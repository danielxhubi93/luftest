package com.example.luftest.repository;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   User findByUsername(String username);
   User findById(int id);
   @Transactional
   @Modifying
   @Query("UPDATE User u set u.username = :username WHERE u.userId = :id")
   void updateUserByUsername(@Param("username") String username, @Param("id") int id);

   @Query("SELECT u FROM User u WHERE u.role = :role")
   List<User> findUserByRoleNamedParams(@Param("role") String role);

   @Query(value = "SELECT * FROM book_db.users u " +
                   " WHERE  u.role = 'USER' and u.id = :user_id " +
                   " and exists (select 1 from book_db.books b where b.user_id = u.id) " +
                   " and exists (select 1 from book_db.orders o where o.user_id = u.id)", nativeQuery = true)
   User findUserByBooksAndOrders(@Param("user_id") int UserId);
}
