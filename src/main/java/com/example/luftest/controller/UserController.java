package com.example.luftest.controller;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import com.example.luftest.service.UserService;
import com.example.luftest.viewmodel.OrderViewModel;
import org.hibernate.SessionFactory;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    public UserController() {

    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return (List<User>) userService.findAll();
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.POST)
    public List<User> remove(@ApiPathParam(name = "username") @PathVariable String username){
        userService.deleteUser(username);
        return userService.findAll();
    }
    @RequestMapping(value = "/update/{username}")
    public List<User> updateUser(@ApiPathParam(name = "username") @PathVariable String username, @RequestBody User user){
        userService.updateUser(user.getUserId(), username);
        return userService.findAll();
    }
    @RequestMapping(value = "/save")
    public List<User>  addUser(@RequestBody User user){
        userService.saveUser(user);
        return userService.findAll();
    }

}
