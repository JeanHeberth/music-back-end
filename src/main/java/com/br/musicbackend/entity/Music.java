package com.br.musicbackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Music {

    @Id
    private String id;

    private String title;
    private String artist;

    private String userId;

}