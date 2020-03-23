package com.example.luftest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idBook;
    @Column(name = "title")
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_added", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date dtAdded = new Date();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
    @Column(name = "book_file")
    @Lob
    private byte[] book_file;

    public Book() {
    }

    public Book(int idBook, String title, String userId, Date dtAdded) {
        this.idBook = idBook;
        this.title = title;
        this.user = new User(userId, "") ;
        this.dtAdded = dtAdded;
    }
    public Book(int id) {
        this.idBook = id;
    }
    public Book(String title, User user, byte[] book_file) {
        this.title = title;
        this.user = user;
        this.book_file = book_file;
    }

    public byte[] getBook_file() {
        return book_file;
    }

    public void setBook_file(byte[] book_file) {
        this.book_file = book_file;
    }

    public String getDtAdded() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(dtAdded);
        return strDate;
    }

    public void setDtAdded(Date dtAdded) {
        this.dtAdded = dtAdded;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
