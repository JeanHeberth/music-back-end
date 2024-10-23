package com.br.musicbackend.repository;

import com.br.musicbackend.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Music, String> {
    List<Music> findByUserId(String userId);
}
