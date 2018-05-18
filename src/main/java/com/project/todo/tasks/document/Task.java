package com.project.todo.tasks.document;

import com.project.todo.tasks.Enum.State;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Task {

    /**
     * Id of the task
     */
    @Id
    private String id ;

    /**
     * task to do
     */
    private String todo;

    /**
     * State of the task
     * Active, Finish
     */
    private State state;

    /**
     * Creation Date of the task
     */
    private LocalDate dateInsert;

    /**
     * modification of the Date of the task
     */
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
