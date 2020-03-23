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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
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
        List<OrderResponse> list = new ArrayList<>();
        Collection<Object[]> lst = orderRepository.findAllByBookUserAndStatus();
        for(Object[] obj : lst){
            list.add(new OrderResponse((int) obj[0],(String) obj[1],(String)  obj[2],(String)   obj[3],(String)  obj[4]));
        }
        return list;
    }
    public List<OrderResponse> findAllByUsername(String username){
        List<OrderResponse> list = new ArrayList<>();
        Collection<Object[]> lst = orderRepository.findAllByUsername(username);
        for(Object[] obj : lst){
            list.add(new OrderResponse((int) obj[0],(String) obj[1],(String)  obj[2],(String)   obj[3],(String)  obj[4]));
        }
        return list;
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public void deleteOrder(int idOrder){
        orderRepository.deleteById(idOrder);
    }

    public void updateOrderStatusById(int id, int status){
        orderRepository.updateOrderStatusById(status,id);
    }

    public List<Order> findByIdUser(int iduser){
        User user = new User(iduser);
        return orderRepository.findByUser(user);
    }
    public List<OrderResponse> getOrders(){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { username = ((UserDetails)principal).getUsername(); }
        else { username = principal.toString(); }

        if (String.valueOf(username).equals("admin")){ orderResponseList = findAllByBookUserAndStatus(); }
        else{ orderResponseList = findAllByUsername(username); }
        return orderResponseList;
    }
}

