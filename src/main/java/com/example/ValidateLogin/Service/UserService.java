package com.example.ValidateLogin.Service;

import com.example.ValidateLogin.Entity.User;
import com.example.ValidateLogin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> loginUser(String username, String password);

}
