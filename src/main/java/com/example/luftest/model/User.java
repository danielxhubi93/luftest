package com.example.luftest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

//Users table
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",  columnDefinition = "INT")
    private int userId;
    @Column(name = "username" ,  columnDefinition = "VARCHAR")
    private String username;
    @Column(name = "password",  columnDefinition = "VARCHAR")
    private String password;
    @Column(name = "role",  columnDefinition = "VARCHAR")
    private String role;
    @Column(name = "activ" , columnDefinition = "TINYINT")
    private boolean activ;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books;

    public User() {
    }
    public User(int userid) {
        this.userId = userid;
    }

    public User(int userId, String username, String password, String role, boolean activ, List<Order> orders, List<Book> books) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.activ = activ;
        this.orders = orders;
        this.books = books;
    }

    public User(int userid, String username, String password, String role, boolean activ) {
        this.userId = userid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.activ = activ;
    }
    public User(String username, String role, boolean activ) {
        this.username = username;
        this.role = role;
        this.activ = activ;
    }
    public User(String username, String password, String role,boolean activ) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.activ = activ;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.activ = true;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.activ = user.getActiv();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isActiv() {
        return activ;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getRole() {
        return  role;
    }

    public void setRole(String role) { this.role = role; }

    public boolean getActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }

}
