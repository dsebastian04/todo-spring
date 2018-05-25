package com.project.todo.tasks.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.todo.tasks.params.TaskState;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public Task() {
    }

    public Task(String id, String todo, TaskState state, LocalDate dateInsert, LocalDate dateModify, User user) {
        this.id = id;
        this.todo = todo;
        this.state = state;
        this.dateInsert = dateInsert;
        this.dateModify = dateModify;
        this.user = user;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(todo, task.todo) &&
                state == task.state &&
                Objects.equals(dateInsert, task.dateInsert) &&
                Objects.equals(dateModify, task.dateModify) &&
                Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, todo, state, dateInsert, dateModify, user);
    }
}
