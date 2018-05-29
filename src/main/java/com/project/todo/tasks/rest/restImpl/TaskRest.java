package com.project.todo.tasks.rest.restImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.rest.ITaskRest;
import com.project.todo.tasks.service.serviceImpl.TaskService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRest implements ITaskRest {

    private TaskService taskService;

    public TaskRest(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @Override
    public Task switchStatus(@PathVariable String id) {
        return taskService.switchStatus(id);
    }

    @Override
    public Task modifyToDO(@RequestBody Task task, @PathVariable String id) {
        return taskService.modifyToDO(task, id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override
    public Task findByIdTask(@PathVariable String id) {
        return taskService.findByIdTask(id);
    }

    @Override
    public List<Task> findByUserNickname(@PathVariable String user) {
        return taskService.findByUserNickname(user);
    }

    @Override
    public void deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }

    @Override
    public Task updateTask(@RequestBody Task task, @PathVariable String id) {
        return taskService.updateTask(task);
    }

}
