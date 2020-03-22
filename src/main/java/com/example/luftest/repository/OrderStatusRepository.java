package com.example.luftest.repository;

import com.example.luftest.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findById(int id);
}
