package com.example.URL_Shortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShortenURLDTO {
    private String longUrl;
    private Integer userId;
}

