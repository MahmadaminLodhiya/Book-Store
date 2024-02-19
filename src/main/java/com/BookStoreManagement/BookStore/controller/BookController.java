package com.BookStoreManagement.BookStore.controller;

import com.BookStoreManagement.BookStore.Dto.AddBookDto;
import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;

import com.BookStoreManagement.BookStore.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class BookController {
    @Autowired
    IBookService _bookService;


    @PostMapping("/AddBook")
    public ResponseEntity<ServicesResponse<String>> AddNewBook(@RequestBody AddBookDto book) {
        ServicesResponse<String> data = _bookService.AddBook(book);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @DeleteMapping(value = "/Book/{Id}")
    public ResponseEntity<ServicesResponse<Optional<Book>>> DeleteBook(@PathVariable Integer Id) {
        ServicesResponse<Optional<Book>> data = _bookService.Delete(Id);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @GetMapping(value = "/BookById/{Id}")
    public ResponseEntity<ServicesResponse<BookDto>> BookById(@PathVariable Integer Id) {
        ServicesResponse<BookDto> data = _bookService.GetById(Id);
        if (data.Success) {
            return ResponseEntity.ok(_bookService.GetById(Id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/BookByTitle/{Title}")
    public ResponseEntity<ServicesResponse<BookDto>> BookByTitle(@PathVariable String Title) {
        ServicesResponse<BookDto> data = _bookService.GetByTitle(Title);
//        if(data.Success) {
        return ResponseEntity.ok(data);
//        }
//        return ResponseEntity.notFound().build();
    }

    @GetMapping("/AllBook")
    public ResponseEntity<ServicesResponse<List<Book>>> GetAllBook() {
        ServicesResponse<List<Book>> data = _bookService.GetAllBook();
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/Book/{Id}")
    public ResponseEntity<ServicesResponse<Book>> UpdateBook(@PathVariable(value = "Id") Integer Id, @RequestBody AddBookDto book) {
        return ResponseEntity.ok(_bookService.Update(Id, book));
    }


}
