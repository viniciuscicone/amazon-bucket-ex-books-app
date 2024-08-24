package com.api.booksManager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "book")
@Entity(name = "book")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    String autor;

}
