package com.br.musicbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@CrossOrigin
@SpringBootApplication
public class MusicBackEndApplication implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir CORS para todas as rotas
                .allowedOrigins("http://localhost:4200") // URL do seu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permitir todos os headers
                .allowCredentials(true); // Permitir credenciais, se necessário
        ;
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicBackEndApplication.class, args);
    }

}
