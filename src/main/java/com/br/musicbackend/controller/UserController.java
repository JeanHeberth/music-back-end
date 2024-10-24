package com.br.musicbackend.controller;


import com.br.musicbackend.dto.UserRequest;
import com.br.musicbackend.dto.UserResponse;
import com.br.musicbackend.entity.User;
import com.br.musicbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        User user = userService.saveUser(userRequest.username(), userRequest.password());
        return ResponseEntity.ok(new UserResponse(user.getUsername()));
    }

}
