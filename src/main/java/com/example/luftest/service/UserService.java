package com.example.luftest.service;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public User findById(int id){
        return userRepository.findById(id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void saveUser(String username,String password){
        User newuser = new User(username, passwordEncoder.encode(password));
        List<User> users = Arrays.asList(newuser);
        // Save to db
        this.userRepository.saveAll(users);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
