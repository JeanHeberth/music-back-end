package com.br.musicbackend.controller;


import ch.qos.logback.classic.encoder.JsonEncoder;
import com.br.musicbackend.entity.User;
import com.br.musicbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Criação de um novo usuário com role padrão
        user.setUsername(user.getUsername());

        user.setPassword(encoder.encode(user.getPassword())); // Lembre-se de criptografar a senha!
        user.setRoles(Set.of("ROLE_USER")); // Por padrão, o usuário terá o papel de 'ROLE_USER'

        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}
