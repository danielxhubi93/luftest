package com.example.luftest.repository;

import com.example.luftest.model.Order;
import com.example.luftest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//Interface to handle Order table
public interface OrderRepository extends JpaRepository<Order, Integer> {

    //Check if a book has an order placed with status Pending or Accepted
    @Query(value = "select * from book_db.orders o " +
            "WHERE book_id = :book_id and o.status_id in (1,3)" , nativeQuery = true)
    Order findByBookAndStatus(@Param("book_id") int bookId);

    //Check if an order has been confirmed (Accepted/Rejected)
    @Query(value = "select * from book_db.orders o " +
            "WHERE id = :order_id and o.status_id in (1,2)" , nativeQuery = true)
    Order findByIdAndStatus (@Param("order_id") int order_id);

    //Check if a book has an order placed by a User  with status  Pending or Accepted
    @Query(value = "select * from book_db.orders o " +
            "WHERE book_id = :book_id and user_id = :user_id  and o.status_id in (1,3)" , nativeQuery = true)
    Order findByBookAndStatusAndUser(@Param("book_id") int bookId,@Param("user_id") int userId);

    //Get all the order by user
    List<Order> findByUser(User user);

    //Update Order status
    @Transactional
    @Modifying
    @Query("UPDATE Order u set u.status.id = :status where u.idOrder = :id")
    void updateOrderStatusById(@Param("status") int status, @Param("id") int id);

    //Get all the orders
    @Query(value = "SELECT o.id as orderId, DATE_FORMAT(o.dt_order, '%d/%m/%Y') as dt_order, b.title, u.username, os.name as status " +
            " FROM orders o " +
            " JOIN books b on b.id = o.book_id" +
            " JOIN users u on u.id = o.user_id" +
            " JOIN orders_status os on os.id = o.status_id", nativeQuery = true)
     public List<Object[]> findAllByBookUserAndStatus();

    //Get all the order by username
    @Query(value = "SELECT o.id as orderId, DATE_FORMAT(o.dt_order, '%d/%m/%Y') as dt_order, b.title, u.username, os.name as status " +
            " FROM orders o " +
            " JOIN books b on b.id = o.book_id" +
            " JOIN users u on u.id = o.user_id" +
            " JOIN orders_status os on os.id = o.status_id WHERE u.username = :username", nativeQuery = true)
    public List<Object[]> findAllByUsername(@Param("username") String username);



}
