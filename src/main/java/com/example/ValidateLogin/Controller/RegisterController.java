package com.example.ValidateLogin.Controller;

import com.example.ValidateLogin.Entity.User;
import com.example.ValidateLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user",new User());
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "Login";
    }
}
