package com.project.todo.tasks.service;

import com.project.todo.tasks.document.Task;
import org.springframework.stereotype.Service;


public interface ITaskService {


    public void createTask(Task task);
}
