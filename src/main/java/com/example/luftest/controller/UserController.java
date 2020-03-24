package com.example.luftest.controller;

import com.example.luftest.model.User;
import com.example.luftest.service.UserService;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//User REST Controller with all the mapping requests for user functions
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {

    }
    //Request to get all Users
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return (List<User>) userService.findAll();
    }

    //Request to delete user by username
    @RequestMapping(value = "/delete/{username}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map removeUser(@ApiPathParam(name = "username") @PathVariable String username){
      String response  =  userService.deleteUser(username);
        return Collections.singletonMap("response", response);
    }

    //Request to update user by username
    @RequestMapping(value = "/update/{username}" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map updateUser(@ApiPathParam(name = "username") @PathVariable String username, @RequestBody User user){
        String response = userService.updateUser(user.getUserId(), username);
        return Collections.singletonMap("response", response);
    }

    //Request to save user
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map addUser(@RequestBody User user){
        String response =  userService.saveUser(user);
        return Collections.singletonMap("response", response);
    }
}
