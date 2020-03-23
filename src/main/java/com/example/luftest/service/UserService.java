package com.example.luftest.service;

import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User findById(int id){
        return userRepository.findById(id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public String saveUser(User user){
        if (user.getUsername() == null || user.getUsername() == "" || user.getPassword() == "" || user.getPassword() == null) {
            return "Error saving the User!";
        }
        if (userRepository.findByUsername(user.getUsername()) != null){
            return "User already exists! Try a different one!";
        }
        else {
            User newuser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()));
            userRepository.save(newuser);
            return "User saved successfully!";
        }
    }
    public String updateUser(int id, String username){
        if (userRepository.findByUsername(username) != null){
            return "User already exists! Try a different one!";
        }
        else {
            userRepository.updateUserByUsername(username, id);
            return "User updated successfully!";
        }
    }
    public String deleteUser(String username){
        User user =  userRepository.findByUsername(username);
        if (userRepository.findUserByBooksAndOrders(user.getUserId()) == null){
            userRepository.delete(user);
            return "User deleted successfully!";
        }
        else { return "User can not be deleted!";}
    }
}
