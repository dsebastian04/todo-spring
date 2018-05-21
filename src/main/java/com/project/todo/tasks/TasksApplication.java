package com.project.todo.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.todo.tasks")
public class TasksApplication {


    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

}
