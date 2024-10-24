package com.br.musicbackend.controller;

import com.br.musicbackend.dto.AlthRequest;
import com.br.musicbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AlthRequest request) {
        String token = authService.authenticate(request.username(), request.password());
        return ResponseEntity.ok(token);
    }
}