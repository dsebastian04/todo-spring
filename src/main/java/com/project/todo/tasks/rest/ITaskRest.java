package com.project.todo.tasks.rest;

import com.project.todo.tasks.document.Task;
import org.springframework.web.bind.annotation.*;

@RequestMapping("tasks")
public interface ITaskRest {

    @PostMapping
    void createTask(@RequestBody Task task);

    @PatchMapping(path = "/{id}/status")
    void switchStatus(@PathVariable String id);
}
