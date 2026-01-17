package com.example.URL_Shortener.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortenURL {
    private String longURL;
    private Integer userId;
}

