package com.example.luftest.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserViewModel {
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
