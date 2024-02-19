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
    public ResponseEntity<ServicesResponse<Optional<Book>>> deletBook(@PathVariable Integer Id) {
        ServicesResponse<Optional<Book>> data = _bookService.delete(Id);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @GetMapping(value = "/BookById/{Id}")
    public ResponseEntity<ServicesResponse<BookDto>> BookByid(@PathVariable Integer Id) {
        ServicesResponse<BookDto> data = _bookService.getbyid(Id);
        if (data.Success) {
            return ResponseEntity.ok(_bookService.getbyid(Id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/BookByTitale/{Title}")
    public ResponseEntity<ServicesResponse<BookDto>> BookBytital(@PathVariable String Title) {
        ServicesResponse<BookDto> data = _bookService.getbytital(Title);
//        if(data.Success) {
        return ResponseEntity.ok(data);
//        }
//        return ResponseEntity.notFound().build();
    }

    @GetMapping("/AllBook")
    public ResponseEntity<ServicesResponse<List<Book>>> getAllBook() {
        ServicesResponse<List<Book>> data = _bookService.getAllBook();
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/Book/{Id}")
    public ResponseEntity<ServicesResponse<Book>> Updatebook(@PathVariable(value = "Id") Integer Id, @RequestBody AddBookDto book) {
        return ResponseEntity.ok(_bookService.update(Id, book));
    }


}
