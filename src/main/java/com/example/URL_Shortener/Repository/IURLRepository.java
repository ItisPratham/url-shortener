package com.example.URL_Shortener.Repository;

import lombok.Lombok;
import com.example.URL_Shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IURLRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortURL(String shortURL);
}
