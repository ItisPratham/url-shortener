package org.example;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class Controller {
    private final Service service;
    private final String baseURL= "www.myURL.com/";

    @PostMapping("/")
    public ResponseEntity<String> createShortURL(@RequestBody ShortenURLDTO shortenURLDTO){
        log.info("Request for creating short url");
        try {
            return ResponseEntity.ok("Demo link");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> fetchLongURL(@PathVariable("url") String url){
        log.info("Request for fetching the long url");
        try {
            return ResponseEntity.ok("Demo link");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
