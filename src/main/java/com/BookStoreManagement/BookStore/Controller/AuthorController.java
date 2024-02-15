package com.BookStoreManagement.BookStore.Controller;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService _authors;
    public AuthorController(AuthorService _authors) {
        this._authors = _authors;
    }
    @PostMapping("/author")
    public ResponseEntity<ServicesResponse<String>> AddAuthor(String name)
    {
        return ResponseEntity.ok(_authors.AddAuthor(name));
    }
    @GetMapping("/GetAllAuthor")
    public ResponseEntity<ServicesResponse<List<Author>>> GetAllAuthor()
    {
        return ResponseEntity.ok(_authors.GetAllAuthor());
    }

}
