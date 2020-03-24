package com.example.luftest.controller;

import com.example.luftest.dto.OrderResponse;
import com.example.luftest.model.Order;
import com.example.luftest.service.OrderService;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Order REST Controller with all the mapping requests for order functions
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController() {
    }
    //Requests to get all the orders
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OrderResponse> getAll(){
        return orderService.getOrders();
    }

    //Requests to change order status by id
    @RequestMapping(value = "/orderstatus/{id}/{status}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map changeStatus(@ApiPathParam(name = "id") @PathVariable int id, @ApiPathParam(name = "status") @PathVariable int status){
      String response =  orderService.updateOrderStatusById(id,status);
        return Collections.singletonMap("response", response);
    }

    //Requests to delete an order
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map deleteOrder(@PathVariable int idOrder){
        String response = orderService.deleteOrder(idOrder);
        return Collections.singletonMap("response", response);
    }

}
