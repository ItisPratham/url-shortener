package com.example.URL_Shortener;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.URL_Shortener.exception.URLNotFound;
import com.example.URL_Shortener.Repository.IURLRepository;
import com.example.URL_Shortener.dto.ShortenURLDTO;
import com.example.URL_Shortener.model.Link;

import java.time.LocalDateTime;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class URLService {
    private final IURLRepository urlRepository;
    //private static int counter = 100000000;
    private static final String ELEMENTS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String base62(long n){
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append(ELEMENTS.charAt((int)(n%62)));
            n /= 62;
        }
        return sb.reverse().toString();
    }

    @Transactional
    public String shortURL(ShortenURLDTO dto){
        log.debug("Received request: longUrl={}, userId={}", dto.getLongUrl(), dto.getUserId());

        final String longURL = dto.getLongUrl();
        final Integer userId = dto.getUserId();
        //final String shortUrl = base62(longURL);

        Link link = Link.builder().longURL(longURL).localDateTime(LocalDateTime.now()).userId(userId).build();
        link = urlRepository.save(link);

        log.debug("Generated link but not shortURL yet");
        String shortUrl = String.format("%5s", base62( link.getId())).replace(' ', '0');

        log.debug("Generated link with shortURL={}, saving to db", shortUrl);
        link.setShortURL(shortUrl);

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