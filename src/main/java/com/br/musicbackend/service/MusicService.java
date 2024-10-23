package com.br.musicbackend.service;


import com.br.musicbackend.entity.Music;
import com.br.musicbackend.entity.User;
import com.br.musicbackend.repository.MusicRepository;
import com.br.musicbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private UserRepository userRepository;

    public Music save(Music music) {
        return musicRepository.save(music);
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    public List<Music> findByUserId(String userId) {
        return musicRepository.findByUserId(userId);
    }

    // Editar música (verificar se o usuário tem permissão)
    public ResponseEntity<?> editMusic(String id, Music updatedMusic, String username) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Música não encontrada");
        }

        Music music = optionalMusic.get();
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        boolean isAdmin = user.get().getRoles().contains("ROLE_ADMIN");

        if (music.getUserId().equals(user.get().getId()) || isAdmin) {
            music.setTitle(updatedMusic.getTitle());
            music.setArtist(updatedMusic.getArtist());
            musicRepository.save(music);
            return ResponseEntity.ok("Música atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para editar esta música.");
        }
    }

    // Apagar música (verificar se o usuário tem permissão)
    public ResponseEntity<?> deleteMusic(String id, String username) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (!optionalMusic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Música não encontrada");
        }

        Music music = optionalMusic.get();

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        boolean isAdmin = user.get().getRoles().contains("ROLE_ADMIN");

        if (music.getUserId().equals(user.get().getId()) || isAdmin) {
            musicRepository.delete(music);
            return ResponseEntity.ok("Música excluída com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para excluir esta música.");
        }
    }
}
