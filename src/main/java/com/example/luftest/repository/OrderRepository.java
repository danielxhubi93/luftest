package com.example.luftest.repository;

import com.example.luftest.dto.OrderResponse;
import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.OrderStatus;
import com.example.luftest.model.User;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.swing.text.html.ObjectView;
import java.time.ZoneOffset;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);
    @Transactional
    @Modifying
    @Query("UPDATE Order u set u.status.id = :status where u.idOrder = :id")
    void updateOrderStatusById(@Param("status") int status, @Param("id") int id);


    @Query(value = "SELECT o.id as orderId, DATE_FORMAT(o.dt_order, '%d/%m/%y') as dt_order, b.title, u.username, os.name as status " +
            " FROM orders o " +
            " JOIN books b on b.id = o.book_id" +
            " JOIN users u on u.id = o.user_id" +
            " JOIN orders_status os on os.id = o.status_id", nativeQuery = true)
     public List<Object> findAllByBookUserAndStatus();

    @Query(value = "SELECT o.id as orderId, DATE_FORMAT(o.dt_order, '%d/%m/%y') as dt_order, b.title, u.username, os.name as status " +
            " FROM orders o " +
            " JOIN books b on b.id = o.book_id" +
            " JOIN users u on u.id = o.user_id" +
            " JOIN orders_status os on os.id = o.status_id WHERE u.username = :username", nativeQuery = true)
    public List<Object> findAllByUsername(@Param("username") String username);



}
