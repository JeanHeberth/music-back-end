package com.br.churchmusicbackend.repository;

import com.br.churchmusicbackend.entitys.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
