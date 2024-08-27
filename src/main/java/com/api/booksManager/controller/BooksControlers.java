package com.api.booksManager.controller;


import com.api.booksManager.domain.Book;
import com.api.booksManager.domain.BookDTO;
import com.api.booksManager.repository.BookRepository;
import com.api.booksManager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")

public class BooksControlers {


    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookrepository;


    @GetMapping
    public ResponseEntity getTest(@Valid @RequestPart("dados") BookDTO livroDTO,
                                  @Valid @RequestPart("imagem") MultipartFile imagem) {

        Book bookrepo = bookService.uploadBookService(livroDTO, imagem);

        /*String url = bookService.uploadImage(imagem);*/

        return ResponseEntity.ok(bookrepo);
    }



/*    @GetMapping
    public ResponseEntity getAllBooks() {

        var Allbooks = bookrepository.findAll();
        return ResponseEntity.ok(Allbooks);
    }

    @PostMapping
    public ResponseEntity postBook(@RequestBody @Valid BookDTO book) {
        Book taskNew = new Book(book);
        bookrepository.save(taskNew);
        return ResponseEntity.ok("Task adicionada = " + taskNew);
    }*/

/*    @DeleteMapping
    public ResponseEntity deteteBook(@RequestBody @Valid IdDTO data, Errors errors) {

        lib.deleteById(data.getId());
        return ResponseEntity.ok("tarefa deletada id : "+  data);
    }*/
 /*   @PutMapping
    public ResponseEntity updateBook(@RequestBody @Valid TaskDTO data, Errors errors) {
        *//*  @PathVariable String id        nos parametros*//*

        Task task = lib.getReferenceById(data.getTask());
        task.setTask(data.getTask());
        task.setAutor(data.getAutor());
        lib.save(task);
        return ResponseEntity.ok("tarefa atualizada : " + data + " - "+ data.getTask() + " - " + data.getAutor());
    }*/
}

