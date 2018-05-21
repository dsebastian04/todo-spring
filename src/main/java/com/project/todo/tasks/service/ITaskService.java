package com.project.todo.tasks.service;

import com.project.todo.tasks.document.Task;


public interface ITaskService {


    void createTask(Task task);

    void switchStatus(String id);
}
