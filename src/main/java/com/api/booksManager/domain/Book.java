package com.api.booksManager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "bookstable")
@Entity(name = "bookstable")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    private String name;
    private String autor;
    private String sinopse;
    private String nota;
    private String img_url;
}
