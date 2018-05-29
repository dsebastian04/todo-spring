package com.project.todo.tasks;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.document.User;
import com.project.todo.tasks.params.TaskState;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckStatusCodes {
    @Test
    public void TestAAWhenCreateTask_then201Created() {
        final String url = "http://localhost:8081/tasks";
        Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).body(task).post(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
    }

    @Test
    public void TestABWhenCreateTaskAndTaskAlreadyExist_then409Conflict() {
        final String url = "http://localhost:8081/tasks";
        Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).body(task).post(url);
        final RequestSpecification postAgain = RestAssured.given();
        final Response responsePostAgain = postAgain.contentType(ContentType.JSON).body(task).post(url);
        Assert.assertThat(responsePostAgain.getStatusCode(), Matchers.equalTo(409));
    }

    @Test
    public void TestBWhenPatchTaskStatus_then200OK() {


        final String url = "http://localhost:8081/tasks/1/status";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).patch(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void TestCWhenPatchTaskToDO_then200OK() {
        final String url = "http://localhost:8081/tasks/1/todo";
        Task task = new Task("1", "modify the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).body(task).patch(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void TestDWhenAllTasksAreRetrieved_then200OK() {
        final String url = "http://localhost:8081/tasks";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.accept(ContentType.JSON).get(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void TestEWhenTaskIsRetrievedByID_then200OK() {
        final String url = "http://localhost:8081/tasks/1";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.accept(ContentType.JSON).get(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void TestFWhenTaskIsRetrievedByNickname_then200OK() {
        final String url = "http://localhost:8081/tasks/nickname/testU";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.accept(ContentType.JSON).get(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }


    @Test
    public void TestGWhenUpdateTask_then200OK() {
        final String url = "http://localhost:8081/tasks/1";
        Task task = new Task("1", "modify whole the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).body(task).put(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }


    @Test
    public void TestHWhenDeleteTask_then204NoContent() {
        final String url = "http://localhost:8081/tasks/1";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.contentType(ContentType.JSON).delete(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(204));
    }


}
