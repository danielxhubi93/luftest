package com.example.luftest.repository;


import com.example.luftest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//Interface to handle User table
public interface UserRepository extends JpaRepository<User, Integer> {
   //Get user by username
   User findByUsername(String username);

   //Get user by id
   User findById(int id);

   //Update user username
   @Transactional
   @Modifying
   @Query("UPDATE User u set u.username = :username WHERE u.userId = :id")
   void updateUserByUsername(@Param("username") String username, @Param("id") int id);

   //Get users by role
   @Query("SELECT u FROM User u WHERE u.role = :role")
   List<User> findUserByRoleNamedParams(@Param("role") String role);

   //Check if user has saved a book or placed an order
   @Query(value = "SELECT * FROM book_db.users u " +
                   " WHERE  u.role = 'USER' and u.id = :user_id " +
                   " and exists (select 1 from book_db.books b where b.user_id = u.id) " +
                   " and exists (select 1 from book_db.orders o where o.user_id = u.id)", nativeQuery = true)
   User findUserByBooksAndOrders(@Param("user_id") int UserId);
}
