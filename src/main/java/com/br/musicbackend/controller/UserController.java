package com.br.musicbackend.controller;


import com.br.musicbackend.dto.UserRegistrationRequest;
import com.br.musicbackend.entity.User;
import com.br.musicbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {
        User user = userService.saveUser(request.username(), request.password());
        return ResponseEntity.ok("User registered: " + user.getUsername());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

}
