package com.example.ValidateLogin.Service;


import com.example.ValidateLogin.Entity.User;
import com.example.ValidateLogin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> loginUser(String username, String rawPassword) {
        Optional<User> userOptional= userRepository.findByUsername(username);

        if(userOptional.isPresent())
        {
            User user= userOptional.get();
            if(passwordEncoder.matches(rawPassword, user.getPassword()))
            {
                return Optional.of(user);
            }

        }
        return Optional.empty();
    }
}
