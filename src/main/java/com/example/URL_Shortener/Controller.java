package com.example.URL_Shortener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.URL_Shortener.exception.URLNotFound;
import com.example.URL_Shortener.dto.ShortenURLDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class Controller {
    private final URLService URLService;
    private final String baseURL= "www.myURL.com/"; // for demo

    @PostMapping("/")
    public ResponseEntity<String> createShortURL(@RequestBody ShortenURLDTO shortenURLDTO){
        log.info("Request for creating short url");
        log.debug("Request body: {}", shortenURLDTO);
        try {
            final String shortURL = URLService.shortURL(shortenURLDTO);
            log.info("Short URL created: {}", shortURL);
            return ResponseEntity.ok(baseURL + shortURL);
        }
        catch (Exception e){
            log.error("Error creating short URL", e);
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> fetchLongURL(@PathVariable("url") String url){
        log.info("Request for fetching the long url");
        try {
            final String longURL = URLService.longURL(url);
            log.info(longURL);
            return ResponseEntity.status(302).body(longURL);
        }
        catch (URLNotFound e){
            return ResponseEntity.status(404).body("URL not found");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/admin/analytic")
    public ResponseEntity<String> getAnalytics(){
        log.info("Request for fetching the analytics for admin");
        try {
            return ResponseEntity.ok("Non functional yet.");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
