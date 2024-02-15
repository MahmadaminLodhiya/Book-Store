package com.BookStoreManagement.BookStore.Controller;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping(value = "/book/{Id}")
    public ResponseEntity<ServicesResponse<Optional<Book>>> deletBook(@PathVariable Integer Id){
        return  ResponseEntity.ok(_bookService.delete(Id));
    }
    @GetMapping(value = "/bookById/{Id}")
    public ResponseEntity<ServicesResponse<Optional<Book>>> BookByid(@PathVariable Integer Id){
        return  ResponseEntity.ok(_bookService.getbyid(Id));
    }
    @GetMapping("/GetAllBook")
    public ResponseEntity<ServicesResponse<List<Book>>> getAllBook(){
        return ResponseEntity.ok(_bookService.getAllBook());
    }




}
