package com.example.luftest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//Define the Order model the client sends to the service
public class OrderRequest {
    private String id;
    @NotNull
    @Min(3)
    private int book_id;

    @NotNull
    private int user_id;

    @NotNull
    private int status_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
