package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Repository.IURLRepository;
import org.example.dto.ShortenURLDTO;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class URLService {
    private final IURLRepository urlRepository;
    private static int counter = 100000000;
    private static final String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String base62(String longUrl){
        int n = counter++;
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append(elements.charAt(n%62));
            n /= 62;
        }
        return sb.reverse().toString();
    }

    public String shortURL(ShortenURLDTO shortenURLDTO){
        final String longURL = shortenURLDTO.getLongUrl();
        final Integer userId = shortenURLDTO.getUserId();
        final String shortUrl = base62(longURL);
        //final Link link = new Link(shortUrl, longURL, LocalDateTime.now(), userId);
        urlRepository.create(shortUrl, longURL);
        return shortUrl;
    }

    public String longURL(String shortURL){
        return urlRepository.read(shortURL);
    }

}
