package com.example.luftest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class OrderResponse {
    private int idOrder;
    private String dt_order;
    private  String title;
    private String username;
    private String status;

    public OrderResponse(int idOrder, String dt_order, String title, String username, String status) {
        this.idOrder = idOrder;
        this.dt_order = dt_order;
        this.title = title;
        this.username = username;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDt_order() {
        return dt_order;
    }

    public void setDt_order(String dt_order) {
        this.dt_order = dt_order;
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
