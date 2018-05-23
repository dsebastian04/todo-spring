package com.project.todo.tasks;

import com.project.todo.tasks.Exception.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;

@ControllerAdvice
public class ControllerAdv extends ResponseEntityExceptionHandler {

    public ControllerAdv() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,getApiError(ex,status), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,getApiError(ex,status), headers, status, request);
    }

    

    private ApiError getApiError(final Exception ex, final HttpStatus httpStatus){
    final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() :ex.getMessage();
    final String devMessage = ExceptionUtils.getRootCauseMessage(ex);

    return new ApiError(httpStatus.value(),message,devMessage);
    }
}
