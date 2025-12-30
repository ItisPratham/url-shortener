package org.example.model;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Link {
    private final String shortURL;
    private final String longURL;
    private final LocalDateTime localDateTime;
    private final Integer userId;
}
