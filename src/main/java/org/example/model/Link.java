package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "links", indexes = @Index(name = "idx_short_url", columnList = "short_url"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", unique = true, nullable = false)
    private String shortURL;

    @Column(name = "long_url", nullable = false)
    private String longURL;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime localDateTime;

    @Column(name = "user_id", nullable = true)
    private Integer userId;
}