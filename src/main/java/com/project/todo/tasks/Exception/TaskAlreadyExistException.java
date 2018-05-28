package com.project.todo.tasks.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "The task already exist, please verify")
public class TaskAlreadyExistException extends RuntimeException {
}
