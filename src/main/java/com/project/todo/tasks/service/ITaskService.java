package com.project.todo.tasks.service;

import com.project.todo.tasks.document.Task;

import java.util.List;


public interface ITaskService {


    void createTask(Task task);

    void switchStatus(String id);

    void modifyToDO( Task task, String id);

    List<Task> getAllTasks();

    Task findByIdTask(String id);
}
