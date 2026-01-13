package com.example.URL_Shortener;

import com.example.URL_Shortener.dto.Response.CreateURLResponse;
import com.example.URL_Shortener.dto.Response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.URL_Shortener.dto.Request.ShortenURL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@RestController
public class Controller {
    private final URLService URLService;

    @PostMapping("/urls")
    public ResponseEntity<CreateURLResponse> createShortURL(@RequestBody ShortenURL shortenURL) {
        log.info("Request for creating short url");
        log.debug("Request body: {}", shortenURL);
        final String shortURL = URLService.shortURL(shortenURL);
        log.info("Short URL created: {}", shortURL);
        return ResponseEntity.ok(new CreateURLResponse(shortURL));
    }

    @GetMapping("/urls/{shortCode}")
    public ResponseEntity<Void> fetchLongURL(@PathVariable("shortCode") String shortCode) {
        log.info("Request for fetching the long url :{}", shortCode);
        final String longURL = URLService.longURL(shortCode);
        log.info(longURL);
        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(longURL))
                .build();
    }

    // not yet implemented
    @GetMapping("/admin/analytic")
    public ResponseEntity<String> getAnalytics() {
        log.info("Request for fetching the analytics for admin");
        return ResponseEntity.ok("Non functional yet.");
    }
}
