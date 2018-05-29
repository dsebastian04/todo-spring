package com.project.todo.tasks;

import com.project.todo.tasks.Exception.ErrorMessage;
import com.project.todo.tasks.Exception.TaskNotFoundException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdv extends ResponseEntityExceptionHandler {

    public ControllerAdv() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,new ErrorMessage("The Body message is mal formed, please contact support",status,status.value()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage("The method argument isn't valid, please verify",status,status.value()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,new ErrorMessage("The request isn't support, please verify",status,status.value()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,new ErrorMessage("The URI doesn't exist, please verify",status,status.value()),new HttpHeaders(),status , request);
    }

    @ExceptionHandler(value = TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleTaskNotFoundException(final RuntimeException ex, final WebRequest request){
        final HttpStatus status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(ex,new ErrorMessage("The task doesn't exist, please verify",status,status.value()),new HttpHeaders(),status , request);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<Object> handleTaskAlreadyExist(final RuntimeException ex, final WebRequest request){
        final HttpStatus status = HttpStatus.CONFLICT;
        return handleExceptionInternal(ex,new ErrorMessage("The task Already exist, please verify ",status,status.value()),new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = {UncategorizedMongoDbException.class, DataAccessResourceFailureException.class})
    public ResponseEntity<Object> handleMongoIsDown(final RuntimeException ex, final WebRequest request) {
        final HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        return handleExceptionInternal(ex, new ErrorMessage("Please apologize us, we have some troubles. O.O' ",status,status.value()), new HttpHeaders(), status, request);
    }

}
