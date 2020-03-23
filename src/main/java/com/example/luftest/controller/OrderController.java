package com.example.luftest.controller;

import com.example.luftest.dto.OrderResponse;
import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.service.BookService;
import com.example.luftest.service.OrderService;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    public OrderController() {
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OrderResponse> getAll(){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (username == "admin"){
            orderResponseList = orderService.findAllByBookUserAndStatus();
        }
        else{
            orderResponseList = orderService.findAllByUsername(username);
        }
        return orderResponseList;
    }

    @RequestMapping(value = "/orderbyuser/{iduser}", method = RequestMethod.GET)
    public List<Order> getOrderByUser(@PathVariable int iduser){
        return orderService.findByIdUser(iduser);
    }

    @RequestMapping(value = "/orderstatus/{id}/{status}", method = RequestMethod.POST)
    public List<OrderResponse> changeStatus(@ApiPathParam(name = "id") @PathVariable int id, @ApiPathParam(name = "status") @PathVariable int status){
        orderService.updateOrderStatusById(id,status);
        return orderService.findAllByBookUserAndStatus();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<OrderResponse> saveorder(@RequestBody Order order){
        orderService.saveOrder(order);
        return orderService.findAllByBookUserAndStatus();
    }
}
