package com.example.luftest.model;


import com.example.luftest.service.OrderService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",  columnDefinition = "INT")
    private int idOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    @JsonIgnore
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_order" , nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date dtOrder= new Date();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="status_id")
    @JsonIgnore
    private OrderStatus status;


    public Order() {
    }
    public Order(int idOrder, Book book, User user, OrderStatus status) {
        this.idOrder = idOrder;
        this.book = book;
        this.user = user;
        this.status = status;

    }


    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDtOrder() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(dtOrder);
        return strDate;
    }

    public void setDtOrder(Date dtOrder) {
        this.dtOrder = dtOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
