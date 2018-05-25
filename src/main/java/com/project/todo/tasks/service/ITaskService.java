package com.project.todo.tasks.service;

import com.project.todo.tasks.Exception.TaskNotFoundException;
import com.project.todo.tasks.document.Task;

import java.util.List;


public interface ITaskService {


    Task createTask(Task task);

    Task switchStatus(String id);

    Task modifyToDO(Task task, String id);

    List<Task> getAllTasks();

    Task findByIdTask(String id) throws TaskNotFoundException;

    List<Task> findByUserNickname(String user);

    void deleteTaskById(String id);

    Task updateTask(Task task);

}
