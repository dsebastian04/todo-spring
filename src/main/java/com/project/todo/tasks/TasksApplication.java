package com.project.todo.tasks;

import com.project.todo.tasks.document.UserAuth;
import com.project.todo.tasks.repository.UserAuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@SpringBootApplication
@EnableWebMvc
public class TasksApplication implements CommandLineRunner {


    private UserAuthRepository userAuthRepository;

    public TasksApplication(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TasksApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        UserAuth auth =  new UserAuth("TaskUser","pass",new ArrayList<>());
        userAuthRepository.save(auth);
    }
}
