package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Exception.URLNotFound;
import org.example.Repository.IURLRepository;
import org.example.dto.ShortenURLDTO;
import org.example.model.Link;

import java.time.LocalDateTime;

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
        log.debug("Received request: longUrl={}, userId={}", shortenURLDTO.getLongUrl(), shortenURLDTO.getUserId());

        final String longURL = shortenURLDTO.getLongUrl();
        final Integer userId = shortenURLDTO.getUserId();
        final String shortUrl = base62(longURL);

        log.debug("Generated short URL: {}", shortUrl);

        Link link = Link.builder()
                .shortURL(shortUrl)
                .longURL(longURL)
                .localDateTime(LocalDateTime.now())
                .userId(userId)
                .build();

        log.debug("Saving link to DB: shortURL={}, longURL={}, userId={}",
                link.getShortURL(), link.getLongURL(), link.getUserId());

        Link saved = urlRepository.save(link);
        log.info("Successfully saved link with ID: {}", saved.getId());

        return shortUrl;
    }

    public String longURL(String shortURL) throws URLNotFound {
        log.debug("Looking up short URL: {}", shortURL);

        Link link = urlRepository.findByShortURL(shortURL)
                .orElseThrow(() -> {
                    log.warn("Short URL not found: {}", shortURL);
                    return new URLNotFound("URL not found: " + shortURL);
                });

        log.info("Found long URL: {}", link.getLongURL());
        return link.getLongURL();
    }
}