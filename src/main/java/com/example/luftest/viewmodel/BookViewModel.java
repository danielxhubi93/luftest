package com.example.luftest.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookViewModel {
    private String id;
    @NotNull
    @Min(3)
    private String title;

    @NotNull
    private int user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
