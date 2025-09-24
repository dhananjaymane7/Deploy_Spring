package com.example.ValidateLogin.Controller;

import com.example.ValidateLogin.Entity.User;
import com.example.ValidateLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user,Model model){

        String username= user.getUsername();
        String rawPassword = user.getPassword();

        Optional<User> authenticatedUser = userService.loginUser(user.getUsername(), user.getPassword());

        if(authenticatedUser.isPresent()){
            model.addAttribute("fullname",authenticatedUser.get().getFullname());
            return "home";
        }
        else {
            model.addAttribute("error","Invalid username and password");
            return "login";
        }
    }

    @GetMapping("/exchange")
    public String exchangePage()
    {
        return "return-exchange";
    }

}
