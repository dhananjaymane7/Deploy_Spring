package com.example.ValidateLogin.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/public-api/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/home", "/exchange", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")         // Use your Thymeleaf login page
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
