package com.project.todo.tasks.rest.restImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.rest.ITaskRest;
import com.project.todo.tasks.service.serviceImpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRest implements ITaskRest {

    @Autowired
    private TaskService taskService;

    @Override
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
