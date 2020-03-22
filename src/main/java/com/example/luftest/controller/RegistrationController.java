package com.example.luftest.controller;

import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import com.example.luftest.service.UserService;
import org.hibernate.SessionFactory;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    public RegistrationController() {

    }
    @RequestMapping(value = "/add/{username}/{password}")
    public String addUser(@ApiPathParam(name = "username") @PathVariable String username,@ApiPathParam(name = "password") @PathVariable String password){
          userService.saveUser(username,password);
          return "OK";
    }
}
