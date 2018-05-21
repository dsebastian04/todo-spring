package com.project.todo.tasks.rest;

import com.project.todo.tasks.document.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("tasks")
public interface ITaskRest {

    @PostMapping
    public void createTask(@RequestBody Task task);
}
