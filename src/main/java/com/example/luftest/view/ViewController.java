package com.example.luftest.view;

import com.example.luftest.model.User;
import com.example.luftest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class ViewController {
    private String appMode;
    private UserRepository userRepository;
    @Autowired
    public ViewController(Environment environment, UserRepository userRepository){
        appMode = environment.getProperty("app-mode");
        this.userRepository = userRepository;
    }
    @GetMapping("home")
    public String home(Model model){
        return "home";
    }
    @GetMapping("admin/users")
    public String users(){
        return "admin/users";
    }

    @GetMapping("orders")
    public String orders(){
        return "orders";
    }

    @GetMapping("admin/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "admin/registration";
    }

    @GetMapping("books")
    public String books(){
        return "books";
    }

    @GetMapping("login")
    public String login(){return "login";}

    @GetMapping("addbook")
    public String addbook(){return "addbook";}


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public int currentUserid(Principal principal) {
       User user = userRepository.findByUsername(principal.getName());
       return  user.getUserId();
    }
}
