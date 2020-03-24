package com.example.luftest.controller;

import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.OrderStatus;
import com.example.luftest.model.User;
import com.example.luftest.service.BookService;
import com.example.luftest.service.OrderService;
import com.example.luftest.service.UserService;
import com.example.luftest.dto.OrderRequest;
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

//Book REST Controller with all the mapping requests for book functions
@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    public BookController() {
    }
    //Request to get all the books
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAll(){
        return bookService.findAll();
    }

    //Request to get all the books by User
    @RequestMapping(value = "/bookbyuser/{iduser}", method = RequestMethod.GET)
    public List<Book> getBookByUser(@PathVariable int iduser){
        return bookService.findByIdUser(iduser);
    }

    //Request to delete a book by id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map removeBook(@ApiPathParam(name = "id") @PathVariable int id){
       String response = bookService.deleteBook(id);
        return Collections.singletonMap("response", response);
    }

    //Request to save book
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map saveBook(@RequestParam(value="file") MultipartFile file) throws IOException {
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

    //Request to order a book
    @RequestMapping(value = "/order/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map saveorder(@RequestBody OrderRequest orderRequest){
        Book book = bookService.findById(orderRequest.getBook_id());
        User user = userService.findById(orderRequest.getUser_id());
        OrderStatus status = orderService.findStatusById(orderRequest.getStatus_id());
        Order order = new Order(0,book,user,status);
        String response = orderService.saveOrder(order);
        return Collections.singletonMap("response", response);
    }
}
