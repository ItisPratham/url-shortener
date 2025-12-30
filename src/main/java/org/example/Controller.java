package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ShortenURLDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class Controller {
    private final URLService URLService;
    private final String baseURL= "www.myURL.com/";

    @PostMapping("/")
    public ResponseEntity<String> createShortURL(@RequestBody ShortenURLDTO shortenURLDTO){
        log.info("Request for creating short url");
        try {
            final String shortURL = URLService.shortURL(shortenURLDTO);
            return ResponseEntity.ok(shortURL);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
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
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/admin/analytic")
    public ResponseEntity<String> getAnalytics(){
        log.info("Request for fetching the analytics for admin");
        try {
            return ResponseEntity.ok("Demo link");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
