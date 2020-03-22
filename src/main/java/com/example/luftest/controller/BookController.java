package com.example.luftest.controller;

import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.OrderStatus;
import com.example.luftest.model.User;
import com.example.luftest.repository.BookRepository;
import com.example.luftest.repository.OrderStatusRepository;
import com.example.luftest.repository.UserRepository;
import com.example.luftest.service.BookService;
import com.example.luftest.service.OrderService;
import com.example.luftest.service.UserService;
import com.example.luftest.viewmodel.BookViewModel;
import com.example.luftest.viewmodel.OrderViewModel;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    public BookController() {
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @RequestMapping(value = "/bookbyuser/{iduser}", method = RequestMethod.GET)
    public List<Book> getBookByUser(@PathVariable int iduser){
        return bookService.findByIdUser(iduser);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public List<Book> remove(@ApiPathParam(name = "id") @PathVariable int id){
        bookService.deleteBook(id);
        return bookService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<Book> savebook(@RequestParam(value="file") MultipartFile file) throws IOException {
        User user = userService.findById(34);
        Book book = new Book(file.getName(), user, file.getBytes());
        bookService.saveBook(book);
        return bookService.findAll();
    }

    @RequestMapping(value = "/order/save", method = RequestMethod.POST)
    public String saveorder(@RequestBody OrderViewModel orderViewModel){
        Book book = bookService.findById(orderViewModel.getBook_id());
        User user = userService.findById(orderViewModel.getUser_id());
        OrderStatus status = orderService.findStatusById(orderViewModel.getStatus_id());
        Order order = new Order(0,book,user,status);
        orderService.saveOrder(order);
        return "OK";
    }
}
