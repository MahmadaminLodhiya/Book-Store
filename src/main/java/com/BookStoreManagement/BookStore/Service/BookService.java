package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository _book;

    public BookService(BookRepository bookRepository){
        _book = bookRepository;
    }

    public ResponseEntity<ServicesResponse<List<Book>>> getAllBook(){
        ServicesResponse<List<Book>> response = new ServicesResponse<List<Book>>();
       response.Data=_book.findAll();
       return ResponseEntity.ok(response);
    }
}
