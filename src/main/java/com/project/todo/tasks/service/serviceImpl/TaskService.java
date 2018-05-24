package com.project.todo.tasks.service.serviceImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.params.TaskState;
import com.project.todo.tasks.repository.TaskRepository;
import com.project.todo.tasks.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
        t.setId(t.getId() + "00");
        return t;
    }

    @Override
    public Task switchStatus(String id) {
        Task task = taskRepository.findOne(id);
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
    public void modifyToDO(Task task, String id) {
        Task lTask = taskRepository.findOne(id);
        if (lTask != null) {
            if (task.getTodo() != null) {
                lTask.setTodo(task.getTodo());
            } else {
                task.setTodo("");
            }
            lTask.setDateModify(LocalDate.now());
            taskRepository.save(lTask);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findByIdTask(String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task findByUserNickname(String user) {
        return taskRepository.findByUser_Nickname(user);
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.delete(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
