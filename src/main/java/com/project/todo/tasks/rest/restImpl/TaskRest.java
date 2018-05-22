package com.project.todo.tasks.rest.restImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.rest.ITaskRest;
import com.project.todo.tasks.service.serviceImpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    @Override
    public void switchStatus(@PathVariable String id) {
        taskService.switchStatus(id);
    }

    @Override
    public void modifyToDO(@RequestBody Task task, @PathVariable String id) {
        taskService.modifyToDO(task, id);
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
    public Task findByUserNickname(@PathVariable String user) {
        return taskService.findByUserNickname(user);
    }

    @Override
    public void deleteTaskById(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }

    @Override
    public void updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
