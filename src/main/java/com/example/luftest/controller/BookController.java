package com.example.luftest.controller;

import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.OrderStatus;
import com.example.luftest.model.User;
import com.example.luftest.repository.OrderStatusRepository;
import com.example.luftest.service.BookService;
import com.example.luftest.service.OrderService;
import com.example.luftest.service.UserService;
import com.example.luftest.viewmodel.OrderViewModel;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map remove(@ApiPathParam(name = "id") @PathVariable int id){
       String response = bookService.deleteBook(id);
        return Collections.singletonMap("response", response);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map savebook(@RequestParam(value="file") MultipartFile file) throws IOException {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        User user = userService.findByUsername(username);
        Book book = new Book(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')), user, file.getBytes());
        String response =  bookService.saveBook(book);
        return Collections.singletonMap("response", response);
    }

    @RequestMapping(value = "/order/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map saveorder(@RequestBody OrderViewModel orderViewModel){
        Book book = bookService.findById(orderViewModel.getBook_id());
        User user = userService.findById(orderViewModel.getUser_id());
        OrderStatus status = orderService.findStatusById(orderViewModel.getStatus_id());
        Order order = new Order(0,book,user,status);
        String response = orderService.saveOrder(order);
        return Collections.singletonMap("response", response);
    }
}
