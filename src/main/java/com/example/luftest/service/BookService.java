package com.example.luftest.service;

import com.example.luftest.model.Book;
import com.example.luftest.model.Order;
import com.example.luftest.model.User;
import com.example.luftest.repository.BookRepository;
import com.example.luftest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
   private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Book findById(int id){
        return bookRepository.findById(id);
    }
    public String saveBook(Book book){
        try {
            bookRepository.save(book);
            return "Book was saved successfully!";
        }
        catch(Exception e) {
            return "Error while saving the book!";
        }
    }
    public String deleteBook(int id){
        Book book = bookRepository.findById(id);
        if (book != null){
            Order order = orderRepository.findByBookAndStatus(book.getIdBook());
            if (order == null){
                bookRepository.deleteById(id);
                return "Book deleted successfully!";
            }
            else{ return "Book can not be deleted! Order exists!"; }
        }
        else { return "Book does not exists!"; }
    }
    public List<Book> findByIdUser(int iduser){
        User user = new User(iduser);
        return bookRepository.findByUser(user);
    }
}
