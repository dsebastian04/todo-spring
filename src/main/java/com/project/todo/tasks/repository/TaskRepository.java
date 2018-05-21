package com.project.todo.tasks.repository;

import com.project.todo.tasks.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task,String> {

}
