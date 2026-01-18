package com.example.URL_Shortener.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "links", indexes = @Index(name = "idx_short_url", columnList = "short_url"))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "short_url_seq_generator")
    @SequenceGenerator(
            name = "short_url_seq_generator",
            sequenceName = "short_url_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "short_url", unique = true)
    private String shortURL;

    @Column(name = "long_url", nullable = true)
    private String longURL;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime localDateTime;

    @Column(name = "user_id", nullable = true)
    private Integer userId;
}
