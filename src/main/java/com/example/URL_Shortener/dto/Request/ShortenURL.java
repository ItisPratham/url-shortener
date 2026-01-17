package com.example.URL_Shortener.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortenURL {
    @JsonProperty("longUrl")
    private String longUrl;
    private Integer userId;
}

