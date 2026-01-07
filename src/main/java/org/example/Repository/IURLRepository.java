package org.example.Repository;

import lombok.Lombok;
import org.example.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IURLRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortURL(String shortURL);
}
