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
    private String id;
    private String name_book;
    private String autor;
    private String sinopse;
    private String img_url;
    private int nota;

}
