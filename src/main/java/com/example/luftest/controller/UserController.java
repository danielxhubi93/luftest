package com.example.luftest.controller;

import com.example.luftest.model.Book;
import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import com.example.luftest.service.UserService;
import com.example.luftest.viewmodel.OrderViewModel;
import org.hibernate.SessionFactory;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map removeUser(@ApiPathParam(name = "username") @PathVariable String username){
      String response  =  userService.deleteUser(username);
        return Collections.singletonMap("response", response);
    }
    @RequestMapping(value = "/update/{username}" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map updateUser(@ApiPathParam(name = "username") @PathVariable String username, @RequestBody User user){
        String response = userService.updateUser(user.getUserId(), username);
        return Collections.singletonMap("response", response);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map addUser(@RequestBody User user){
        String response =  userService.saveUser(user);
        return Collections.singletonMap("response", response);
    }

}
