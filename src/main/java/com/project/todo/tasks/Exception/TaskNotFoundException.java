package com.project.todo.tasks.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task(s) does not exist, please review your criteria")
public class TaskNotFoundException extends RuntimeException {

}
