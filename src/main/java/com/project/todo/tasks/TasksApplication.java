package com.project.todo.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TasksApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TasksApplication.class, args);
    }

}
