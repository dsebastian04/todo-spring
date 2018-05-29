package com.project.todo.tasks;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.project.todo.tasks.Exception.ErrorMessage;
import com.project.todo.tasks.configuration.MongoConfiguration;
import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.document.User;
import com.project.todo.tasks.params.TaskState;
import org.apache.tomcat.jni.Local;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckOperations {

    @Test
    public void testAWhenCreateTask_thenCanRetrieveSameTask() {
        final String url = "http://localhost:8081/tasks";
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification postSpecification = RestAssured.given();
        final Response response = postSpecification.contentType(ContentType.JSON).body(task).post(url);

        final RequestSpecification readRequestSpecification = RestAssured.given();
        final Task taskRetrieved = readRequestSpecification.accept(ContentType.JSON).get(url.concat("/1")).as(Task.class);
        Assert.assertThat(task, equalTo(taskRetrieved));
    }

    @Test
    public void testBWhenPatchStatusTask_thenCanReviewTheStatusChange() {

        final String url = "http://localhost:8081/tasks/1";

        final RequestSpecification readOne = RestAssured.given();
        final Task taskRead = readOne.accept(ContentType.JSON).get(url).as(Task.class);

        final RequestSpecification patch = RestAssured.given();
        patch.patch(url.concat("/status"));

        final RequestSpecification readTwo = RestAssured.given();
        final Task taskModify = readTwo.accept(ContentType.JSON).get(url).as(Task.class);


        Assert.assertThat(taskRead.getState(), is(not(taskModify.getState())));
    }

    @Test
    public void testCWhenPatchToDOTask_thenCanReviewToDoChange() {

        final String url = "http://localhost:8081/tasks/1";

        final Random m = new Random();
        final Task task = new Task("1", "patch the test  " + (m.nextInt() * 11), TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification patch = RestAssured.given();
        patch.body(task).contentType(ContentType.JSON).patch(url.concat("/todo"));

        final RequestSpecification readTwo = RestAssured.given();
        final Task taskModify = readTwo.accept(ContentType.JSON).get(url).as(Task.class);


        Assert.assertThat(task.getTodo(), equalTo(taskModify.getTodo()));
        Assert.assertThat(taskModify.getDateModify(), equalTo(LocalDate.now()));
    }

    @Test
    public void testDWhenPutTask_thenCanReviewChange() {

        final String url = "http://localhost:8081/tasks/1";


        final RequestSpecification readOne = RestAssured.given();
        final Task taskRead = readOne.accept(ContentType.JSON).get(url).as(Task.class);

        Task taskPut = taskRead;
        taskPut.setTodo("test " + Math.random());
        taskPut.getUser().setName("test");

        final RequestSpecification put = RestAssured.given();
        put.body(taskPut).contentType(ContentType.JSON).put(url);

        final RequestSpecification readTwo = RestAssured.given();
        final Task taskModify = readTwo.contentType(ContentType.JSON).get(url).as(Task.class);


        Assert.assertThat(taskPut, equalTo(taskModify));
    }

    @Test
    public void testEWhenDeleteTask_thenCantRetrieveSameTask() {

        final String url = "http://localhost:8081/tasks/1";

        final RequestSpecification delete = RestAssured.given();
        delete.delete(url);

        final RequestSpecification readTwo = RestAssured.given();
        final ErrorMessage taskModify = readTwo.contentType(ContentType.JSON).get(url).as(ErrorMessage.class);

        final ErrorMessage result = new ErrorMessage("The task doesn't exist, please verify",HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value());

        Assert.assertThat(result, equalTo(taskModify));
    }
}
