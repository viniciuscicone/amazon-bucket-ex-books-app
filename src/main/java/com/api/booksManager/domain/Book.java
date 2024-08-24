package com.api.booksManager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "Book")
@Entity
public class Book {

    @Id
    @GeneratedValue
    UUID id;
    String name;
}
