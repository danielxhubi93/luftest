package com.example.luftest.repository;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
  List<Book> findByUser(User user);
  Book findById(int id);
}
