package com.example.luftest.repository;

import com.example.luftest.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface to handle Order Status table
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    //Get all the Order Status by id
    OrderStatus findById(int id);
}
