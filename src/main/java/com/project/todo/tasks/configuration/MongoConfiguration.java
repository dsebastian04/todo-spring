package com.project.todo.tasks.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.project.todo.tasks.repository")
public class MongoConfiguration {

}
