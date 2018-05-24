package com.project.todo.tasks.service;

import com.project.todo.tasks.document.Task;

import java.util.List;


public interface ITaskService {


    Task createTask(Task task);

    Task switchStatus(String id);

    void modifyToDO(Task task, String id);

    List<Task> getAllTasks();

    Task findByIdTask(String id);

    Task findByUserNickname(String user);

    void deleteTaskById(String id);

    void updateTask(Task task);

}
