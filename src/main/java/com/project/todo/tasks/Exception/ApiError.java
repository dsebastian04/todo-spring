package com.project.todo.tasks.Exception;

import java.util.Objects;

public class ApiError {

    private int httpStatus;
    private String message;
    private String developerMessage;

    public ApiError(int httpStatus, String message, String developerMessage) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiError apiError = (ApiError) o;
        return httpStatus == apiError.httpStatus &&
                Objects.equals(message, apiError.message) &&
                Objects.equals(developerMessage, apiError.developerMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(httpStatus, message, developerMessage);
    }
}
