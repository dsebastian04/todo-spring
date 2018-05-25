package com.project.todo.tasks.rest;

import com.project.todo.tasks.document.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("tasks")
public interface ITaskRest {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Task createTask(@RequestBody Task task);

    @PatchMapping(path = "/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    Task switchStatus(@PathVariable String id);

    @PatchMapping(path = "/{id}/todo")
    @ResponseStatus(HttpStatus.OK)
    Task modifyToDO(@RequestBody Task task, @PathVariable String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Task> getAllTasks();

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Task findByIdTask(@PathVariable String id);

    @GetMapping(path = "nickname/{user}")
    @ResponseStatus(HttpStatus.OK)
    List<Task> findByUserNickname(@PathVariable String user);

    @DeleteMapping(path ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTaskById(@PathVariable String id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Task updateTask(@RequestBody Task task,@PathVariable String id);
}
