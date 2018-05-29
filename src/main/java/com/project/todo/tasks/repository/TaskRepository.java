package com.project.todo.tasks.repository;

import com.project.todo.tasks.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    /**
     * Returns all instances that match with the user nickname.
     *
     * @param user nickname of the user
     * @return List tasks of the user
     */
    List<Task> findByUser_Nickname(String user);
}
