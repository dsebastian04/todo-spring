package com.project.todo.tasks.preconditions;

import com.project.todo.tasks.Exception.TaskNotFoundException;
import com.project.todo.tasks.document.Task;

import java.util.List;

public class TaskConditions {

    public static void exist(Task task) {
        if (task == null) {
            throw new TaskNotFoundException();
        }
    }

    public static void exist(List<Task> list) {
        if (list.isEmpty()) {
            throw new TaskNotFoundException();
        }

    }
}
