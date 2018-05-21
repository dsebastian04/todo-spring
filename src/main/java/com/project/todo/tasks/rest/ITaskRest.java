package com.project.todo.tasks.rest;

import com.project.todo.tasks.document.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("tasks")
public interface ITaskRest {

    @PostMapping
    void createTask(@RequestBody Task task);

    @PatchMapping(path = "/{id}/status")
    void switchStatus(@PathVariable String id);

    @PatchMapping(path = "/{id}/todo")
    void modifyToDO(@RequestBody Task task, @PathVariable String id);

    @GetMapping
    List<Task> getAllTasks();

    @GetMapping(path = "/{id}")
    Task findByIdTask(@PathVariable String id);

    @GetMapping(path ="nickname/{user}")
    Task findByUserNickname(@PathVariable String user);
}
