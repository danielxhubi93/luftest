package com.example.luftest.repository;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Interface to handle book table
public interface BookRepository extends JpaRepository<Book,Integer> {
  //Get List of Books by User
  List<Book> findByUser(User user);
  //Get Book by id
  Book findById(int id);
  //Get Book by title
  Book findByTitle(String title);
}
