package com.br.musicbackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    private String id;

    private String username;
    private String password;
    private Set<String> roles;
    private boolean isAdmin;

    public User(String token, String username) {
        this.username = username;
        this.password = token;
        this.roles = Set.of("ROLE_USER");
        this.isAdmin = false;
    }
}