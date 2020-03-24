package com.example.luftest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//Orders Status table
@Entity
@Table(name="orders_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",  columnDefinition = "INT")
    private int id;
    @Column(name = "name",  columnDefinition = "VARCHAR")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
    public OrderStatus(){

    }

    public OrderStatus(int id, String name){
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
    }
    public OrderStatus(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
