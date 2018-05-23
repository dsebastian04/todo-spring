package com.project.todo.tasks;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.project.todo.tasks.configuration.MongoConfiguration;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.regex.Matcher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class,TasksApplication.class},loader = AnnotationConfigContextLoader.class)
public class GetAllTasks {

    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Test
    public void whenAllTasksAreRetrieved_then200OK(){
        final String url = "http://localhost:8081/tasks";
        final RequestSpecification requestSpecification = RestAssured.given();
        final Response response = requestSpecification.accept(JSON).get(url);
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }
}
