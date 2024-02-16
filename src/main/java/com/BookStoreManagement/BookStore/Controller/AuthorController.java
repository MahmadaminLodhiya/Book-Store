package com.BookStoreManagement.BookStore.Controller;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Service.AuthorService;
import com.BookStoreManagement.BookStore.Service.IAuthoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final IAuthoreService _authors;
    public AuthorController(IAuthoreService _authors) {
        this._authors = _authors;
    }

    @PostMapping("/author")
    public ResponseEntity<ServicesResponse<String>> addAuthor(@RequestBody AuthorDto author)
    {
        ServicesResponse<String> data = _authors.AddAuthor(author);
        if(data.Success)
            return ResponseEntity.ok(data);
        return  ResponseEntity.badRequest().body(data);
    }
    @GetMapping("/GetAllAuthor")
    public ResponseEntity<ServicesResponse<List<Author>>> getAllAuthor()
    {
        ServicesResponse<List<Author>> data = _authors.GetAllAuthor();
        if(data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/getAllBooksOfAuthor/{authorId}")
    public ResponseEntity<ServicesResponse<List<Book>>> getAllBooksOfAuthor(@PathVariable Integer authorId){
        ServicesResponse<List<Book>> data = _authors.getAllBookOfAuthor(authorId);
        if(data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<ServicesResponse<Author>> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDto authorDto)
    {
        ServicesResponse<Author> data = _authors.updateAuthor(id,authorDto);
        if(data.Success)
            return ResponseEntity.ok(data);
        return ResponseEntity.badRequest().body(data);
    }
    @GetMapping("/getAuthorById/{authorId}")
    public ResponseEntity<ServicesResponse<AuthorDto>> getAuthorById(@PathVariable Integer authorId)
    {
        ServicesResponse<AuthorDto> data = _authors.getAuthorById(authorId);
        if(data.Success)
            return ResponseEntity.ok((data));
        return ResponseEntity.notFound().build();
    }
}
