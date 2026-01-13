package com.example.URL_Shortener.exception;

public class URLNotFound extends RuntimeException {
    public URLNotFound(final String e) {
        super(e);
    }
}
