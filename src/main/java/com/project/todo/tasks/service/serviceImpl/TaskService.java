package com.project.todo.tasks.service.serviceImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.params.State;
import com.project.todo.tasks.repository.TaskRepository;
import com.project.todo.tasks.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void switchStatus(String id) {
        Task task = taskRepository.findOne(id);
        if (task != null) {
            if (task.getState() != null) {
                if (task.getState().equals(State.Active)) {
                    task.setState(State.Finish);
                } else {
                    task.setState(State.Active);
                }
            } else {
                task.setState(State.Active);
            }

            task.setDateModify(LocalDate.now());
            taskRepository.save(task);
        }
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
