package com.project.todo.tasks.repository;

import com.project.todo.tasks.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Task findByUser_Nickname(String user);
}
