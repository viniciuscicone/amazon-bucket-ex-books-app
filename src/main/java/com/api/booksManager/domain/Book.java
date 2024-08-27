package com.api.booksManager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Builder
@Table(name = "bookstable")
@Entity(name = "bookstable")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    private String nameBook;
    private String autor;
    private String sinopse;
    private String img_url;
    private int nota;

    public Book(BookDTO book) {
    }
}
