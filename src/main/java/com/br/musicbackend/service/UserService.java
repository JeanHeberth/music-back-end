package com.br.musicbackend.service;

import com.br.musicbackend.entity.User;
import com.br.musicbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // Criação de um novo usuário com role padrão
        user.setUsername(user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
