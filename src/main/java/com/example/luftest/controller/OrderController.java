package com.example.luftest.controller;

import com.example.luftest.dto.OrderResponse;
import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.service.BookService;
import com.example.luftest.service.OrderService;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    public OrderController() {
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OrderResponse> getAll(){
        return orderService.getOrders();
    }

    @RequestMapping(value = "/orderstatus/{id}/{status}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map changeStatus(@ApiPathParam(name = "id") @PathVariable int id, @ApiPathParam(name = "status") @PathVariable int status){
      String response =  orderService.updateOrderStatusById(id,status);
        return Collections.singletonMap("response", response);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<OrderResponse> saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return orderService.findAllByBookUserAndStatus();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<OrderResponse> deleteOrder(@PathVariable int idOrder){
        orderService.deleteOrder(idOrder);
        return orderService.getOrders();
    }

}
