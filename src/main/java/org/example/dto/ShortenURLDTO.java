package org.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShortenURLDTO {
    private final String longUrl;
    private final Integer userId;
}
