package org.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShortenURLDTO {
    // private final we can have email and all sorts of things for tracking
    private final String url;
}
