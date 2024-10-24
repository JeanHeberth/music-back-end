package com.br.musicbackend.controller;


import com.br.musicbackend.entity.Music;
import com.br.musicbackend.infra.jwt.JwtUtil;
import com.br.musicbackend.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {


    @Autowired
    private MusicService musicService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/all")
    public ResponseEntity<?> getAllMusic(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);

        if (jwtUtil.validateToken(jwtToken, username)) {
            List<Music> musicList = musicService.findAll();
            return ResponseEntity.ok(musicList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> addMusica(@RequestBody Music musica, Authentication authentication) {
        // Pega o usuário logado (sub no JWT)
        String username = authentication.getName();
        musica.setUserId(username);  // Define o criador como o usuário logado
        Music savedMusica = musicService.save(musica);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMusica);
    }
}
