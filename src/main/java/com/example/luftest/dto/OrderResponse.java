package com.example.luftest.dto;

import javax.persistence.Embedded;

//Define the Order model to display to the client
public class OrderResponse {
    @Embedded
    private int idOrder;
    @Embedded
    private String dtOrder;
    @Embedded
    private  String title;
    @Embedded
    private String username;
    @Embedded
    private String status;

    //Constructor
    public OrderResponse(int idOrder, String dtOrder, String title, String username, String status) {
        this.idOrder = idOrder;
        this.dtOrder = dtOrder;
        this.title = title;
        this.username = username;
        this.status = status;
    }

    //Getters and setters
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDtOrder() {
        return dtOrder;
    }

    public void setDtOrder(String dtOrder) {
        this.dtOrder = dtOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
