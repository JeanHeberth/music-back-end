package com.br.musicbackend.controller;
import com.br.musicbackend.entity.User;
import com.br.musicbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> authenticatedUser = authService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser.isPresent()) {
            // Aqui você geraria e retornaria o token JWT
            return ResponseEntity.ok("Login successful, JWT token here");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
