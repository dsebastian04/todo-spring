package com.project.todo.tasks.Exception;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

    private String content;
    private HttpStatus status;
    private int statusCode;

    public ErrorMessage(String content, HttpStatus status,int statusCode) {
        this.content = content;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
