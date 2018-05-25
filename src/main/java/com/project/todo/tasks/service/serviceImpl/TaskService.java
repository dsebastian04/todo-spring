package com.project.todo.tasks.service.serviceImpl;

import com.project.todo.tasks.Exception.TaskNotFoundException;
import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.params.TaskState;
import com.project.todo.tasks.preconditions.TaskConditions;
import com.project.todo.tasks.repository.TaskRepository;
import com.project.todo.tasks.service.ITaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements ITaskService {


    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        Task t = taskRepository.save(task);
        return t;
    }

    @Override
    public Task switchStatus(String id) throws TaskNotFoundException{
        Task task = taskRepository.findOne(id);
        TaskConditions.exist(task);
        Task result = null;
        if (task != null) {
            if (task.getState() != null) {
                if (task.getState().equals(TaskState.Active)) {
                    task.setState(TaskState.Finish);
                } else {
                    task.setState(TaskState.Active);
                }
            } else {
                task.setState(TaskState.Active);
            }

            task.setDateModify(LocalDate.now());
            result = taskRepository.save(task);
        }
        return result;
    }

    @Override
    public Task modifyToDO(Task task, String id) throws TaskNotFoundException{
        Task lTask = taskRepository.findOne(id);
        TaskConditions.exist(lTask);
        Task result = null;
        if (lTask != null) {
            if (task.getTodo() != null) {
                lTask.setTodo(task.getTodo());
            } else {
                task.setTodo("");
            }
            lTask.setDateModify(LocalDate.now());
             result = taskRepository.save(lTask);
        }
        return result;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findByIdTask(String id) throws TaskNotFoundException {
        Task result = taskRepository.findOne(id);
        TaskConditions.exist(result);
        return result;
    }

    @Override
    public List<Task> findByUserNickname(String user) throws TaskNotFoundException {
        List tasks = taskRepository.findByUser_Nickname(user);
        TaskConditions.exist(tasks);
        return taskRepository.findByUser_Nickname(user);
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.delete(id);
    }

    @Override
    public Task updateTask(Task task) {
        task.setDateModify(LocalDate.now());
        return taskRepository.save(task);
    }
}
