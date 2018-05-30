package com.project.todo.tasks.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class UserAuth {
    @Id
    private String username;
    private String Password;
    private List<String> Roles;

    public UserAuth() {
    }

    public UserAuth(String username, String password, List<String> roles) {
        this.username = username;
        Password = password;
        Roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> roles) {
        Roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return Objects.equals(username, userAuth.username) &&
                Objects.equals(Password, userAuth.Password) &&
                Objects.equals(Roles, userAuth.Roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, Password, Roles);
    }
}
