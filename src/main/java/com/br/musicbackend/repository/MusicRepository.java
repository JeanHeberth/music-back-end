package com.br.churchmusicbackend.repository;

import com.br.churchmusicbackend.entitys.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Music, String> {
    List<Music> findByUserId(String userId);
}
