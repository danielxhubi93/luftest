package com.example.luftest.service;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import com.example.luftest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
   private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Book findById(int id){
        return bookRepository.findById(id);
    }
    public void saveBook(Book book){
         bookRepository.save(book);
    }
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
    public List<Book> findByIdUser(int iduser){
        User user = new User(iduser);
        return bookRepository.findByUser(user);
    }
}
