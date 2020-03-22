package com.example.luftest.service;

import com.example.luftest.dto.OrderResponse;
import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.OrderStatus;
import com.example.luftest.model.User;
import com.example.luftest.repository.BookRepository;
import com.example.luftest.repository.OrderRepository;
import com.example.luftest.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatus findStatusById(int id){
        return orderStatusRepository.findById(id);
    }

    public List<OrderResponse> findAllByBookUserAndStatus(){
        List<Object> obj = orderRepository.findAllByBookUserAndStatus();

        @SuppressWarnings("unchecked")
        List<OrderResponse> list = (List<OrderResponse>)(List<?>)obj;
        return list;
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public void updateOrderStatusById(int id, int status){
        orderRepository.updateOrderStatusById(id,status);
    }

    public List<Order> findByIdUser(int iduser){
        User user = new User(iduser);
        return orderRepository.findByUser(user);
    }
}

