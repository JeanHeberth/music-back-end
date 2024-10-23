package com.br.musicbackend.controller;


import com.br.musicbackend.entity.Music;
import com.br.musicbackend.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {


    @Autowired
    private MusicService musicService;

    @PostMapping
    public ResponseEntity<Music> createMusic(@RequestBody Music music) {
        Music createdMusic = musicService.save(music);
        return ResponseEntity.ok(createdMusic);
    }

    @GetMapping
    public ResponseEntity<List<Music>> getAllMusic() {
        List<Music> musicList = musicService.findAll();
        return ResponseEntity.ok(musicList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Music>> getMusicByUserId(@PathVariable String userId) {
        List<Music> musicList = musicService.findByUserId(userId);
        return ResponseEntity.ok(musicList);
    }

    // Editar música
    @PutMapping("/{id}")
    public ResponseEntity<?> editMusic(@PathVariable String id, @RequestBody Music updatedMusic) {
//        String username = authentication.getName(); // Pega o nome do usuário autenticado
        return musicService.editMusic(id, updatedMusic, "");
    }

    // Apagar música
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable String id) {
//        String username = authentication.getName(); // Pega o nome do usuário autenticado
        return musicService.deleteMusic(id, "Jean");
    }
}
