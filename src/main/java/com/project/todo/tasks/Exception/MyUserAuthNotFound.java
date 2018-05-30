package com.project.todo.tasks.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "username was not found")
public class MyUserAuthNotFound extends RuntimeException {
}
