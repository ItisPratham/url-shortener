package org.example.Repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryDB implements IURLRepository{
    final private HashMap<String, String> stol = new HashMap<>();

    @Override
    public void create(String shortUrl, String longUrl) {
        stol.put(shortUrl, longUrl);
    }

    @Override
    public String read(String shortUrl) {
        return stol.getOrDefault(shortUrl, null);
    }
}
