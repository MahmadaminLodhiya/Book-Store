package com.BookStoreManagement.BookStore.controller;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Service.IAuthoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Authors")
public class AuthorController {
    private final IAuthoreService _authors;

    public AuthorController(IAuthoreService _authors) {
        this._authors = _authors;
    }
// add  new author
    @PostMapping("/Author")
    public ResponseEntity<ServicesResponse<String>> AddAuthor(@RequestBody AuthorDto author) {
        ServicesResponse<String> data = _authors.AddAuthor(author);
        if (data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.badRequest().body(data);
    }
// get all author details
    @GetMapping("/GetAllAuthor")
    public ResponseEntity<ServicesResponse<List<Author>>> GetAllAuthor() {
        ServicesResponse<List<Author>> data = _authors.GetAllAuthor();
        if (data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.notFound().build();
    }
// get all books of author
    @GetMapping("/GetAllBooksOfAuthor/{authorId}")
    public ResponseEntity<ServicesResponse<List<Book>>> GetAllBooksOfAuthor(@PathVariable Integer authorId) {
        ServicesResponse<List<Book>> data = _authors.GetAllBookOfAuthor(authorId);
        if (data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/UpdateAuthor/{id}")
    public ResponseEntity<ServicesResponse<Author>> UpdateAuthor(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        ServicesResponse<Author> data = _authors.UpdateAuthor(id, authorDto);
        if (data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.badRequest().body(data);
    }

    @PatchMapping("/UpdateAuthor/{id}")
    public ResponseEntity<ServicesResponse<Author>> UpdateAuthor(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        ServicesResponse<Author> data = _authors.UpdateSpecificField(id, fields);
        if (data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.badRequest().body(data);
    }


    @GetMapping("/GetAuthorById/{authorId}")
    public ResponseEntity<ServicesResponse<AuthorDto>>GetAuthorById(@PathVariable Integer authorId) {
        ServicesResponse<AuthorDto> data = _authors.GetAuthorById(authorId);
        if (data.Success)
            return ResponseEntity.ok((data));
        return ResponseEntity.notFound().build();
    }
}
