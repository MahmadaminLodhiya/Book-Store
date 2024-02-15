package com.BookStoreManagement.BookStore.Controller;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Service.BookService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService _bookService;

    public BookController(BookService bookService){
        _bookService = bookService;
    }


     @PostMapping("/book")
   public ResponseEntity<ServicesResponse<String>> AddNewBook(@RequestBody Book book){
        return  ResponseEntity.ok(_bookService.AddBook(book));
     }
    @GetMapping("/GetAllBook")
    public ResponseEntity<ServicesResponse<List<Book>>> getAllBook(){
        return ResponseEntity.ok( _bookService.getAllBook());
    }



}