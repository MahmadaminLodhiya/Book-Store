package com.BookStoreManagement.BookStore.controller;

import com.BookStoreManagement.BookStore.Dto.*;
import com.BookStoreManagement.BookStore.Entity.Book;

import com.BookStoreManagement.BookStore.Service.IBookService;
import com.BookStoreManagement.BookStore.enums.OrderBy;
import com.BookStoreManagement.BookStore.enums.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class BookController {
    @Autowired
    IBookService _bookService;


    // add a new book
    @PostMapping("/AddBook")
    public ResponseEntity<ServicesResponse<Book>> AddNewBook(@RequestBody AddBookDto book) {
        ServicesResponse<Book> data = _bookService.AddBook(book);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    // delete a book
    @DeleteMapping("/Book/{Id}")
    public ResponseEntity<ServicesResponse<Optional<Book>>> DeleteBook(@PathVariable Integer Id) {
        ServicesResponse<Optional<Book>> data = _bookService.Delete(Id);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    // get book by id
    @GetMapping("/BookById/{Id}")
    public ResponseEntity<ServicesResponse<BookDto>> BookById(@PathVariable Integer Id) {
        ServicesResponse<BookDto> data = _bookService.GetById(Id);
        if (data.Success) {
            return ResponseEntity.ok(_bookService.GetById(Id));
        }
        return ResponseEntity.notFound().build();
    }

    // get all books
    @GetMapping("/AllBook")
    public ResponseEntity<ServicesResponse<PagingResponse<List<Book>>>> GetAllBook(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber ,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "Sort By" , required = false, defaultValue = "price")SortBy sortBy,
            @RequestParam(value = "Order By" , required = false, defaultValue = "asc")OrderBy orderBy
            ) {

        ServicesResponse<PagingResponse<List<Book>>> data = _bookService.GetAllBook(pageNumber, pageSize,sortBy,orderBy);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    // update whole book
    @PutMapping("/Book/{Id}")
    public ResponseEntity<ServicesResponse<Book>> UpdateBook(@PathVariable(value = "Id") Integer Id, @RequestBody AddBookDto book) {
        return ResponseEntity.ok(_bookService.Update(Id, book));
    }

    // update specific details
    @PatchMapping("/Book/{id}")
    public ResponseEntity<ServicesResponse<Book>> UpdateProductFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(_bookService.updateProductByFields(id, fields));
    }

    @GetMapping("/SearchBook{priceRange}")
    public ResponseEntity<ServicesResponse<PagingResponse<List<Book>>>> SearchBooks(
            @RequestParam(name = "searchTerm", required = false) String searchTerm,
            @RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "Sort By" , required = false, defaultValue = "price")SortBy sortBy,
            @RequestParam(value = "Order By" , required = false, defaultValue = "asc")OrderBy orderBy,
            @RequestParam(value = "minPrice" , required = false, defaultValue = "0") Integer minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "0") Integer maxPrice,
            @RequestParam(value = "minYear" , required = false, defaultValue = "0") Integer minYear,
            @RequestParam(value = "maxYear", required = false, defaultValue = "0") Integer maxYear
            ){
        ServicesResponse<PagingResponse<List<Book>>> data = _bookService.SearchBook(searchTerm,pageNumber, pageSize,sortBy,orderBy,minPrice,maxPrice,minYear,maxYear);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }
}
