package com.project.todo.tasks.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.todo.tasks.params.TaskState;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Task {

    /**
     * Id of the task
     */
    @Id
    private String id;

    /**
     * task to do
     */
    private String todo;

    /**
     * TaskState of the task
     * Active, Finish
     */
    private TaskState state;

    /**
     * Creation Date of the task
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateInsert;

    /**
     * modification of the Date of the task
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateModify;

    /**
     * User who created the task
     */
    private User user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public LocalDate getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(LocalDate dateInsert) {
        this.dateInsert = dateInsert;
    }

    public LocalDate getDateModify() {
        return dateModify;
    }

    public void setDateModify(LocalDate dateModify) {
        this.dateModify = dateModify;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
