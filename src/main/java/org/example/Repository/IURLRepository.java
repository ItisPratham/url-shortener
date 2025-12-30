package org.example.Repository;

public interface IURLRepository {
    void create(String shortUrl, String longUrl);
    String read(String shortUrl);
}
