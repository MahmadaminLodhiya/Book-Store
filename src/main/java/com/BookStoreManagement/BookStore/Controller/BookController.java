package com.BookStoreManagement.BookStore.Controller;

import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Service.BookService;

import com.BookStoreManagement.BookStore.Service.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class BookController {

    private final IBookService _bookService;

    public BookController(IBookService bookService){
        _bookService = bookService;
    }


     @PostMapping("/AddBook")
   public ResponseEntity<ServicesResponse<String>> AddNewBook(@RequestBody Book book){
         ServicesResponse<String> data =_bookService.AddBook(book);
         if(data.Success){
          return  ResponseEntity.ok(data);
         }
         return ResponseEntity.badRequest().body(data);
     }

    @DeleteMapping(value = "/book/{Id}")
    public ResponseEntity<ServicesResponse<Optional<Book>>> deletBook(@PathVariable Integer Id){
        ServicesResponse<Optional<Book>> data =_bookService.delete(Id);
        if(data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }
    @GetMapping(value = "/bookById/{Id}")
    public ResponseEntity<ServicesResponse<BookDto>> BookByid(@PathVariable Integer Id){
        ServicesResponse<BookDto> data = _bookService.getbyid(Id);
        if(data.Success) {
            return ResponseEntity.ok(_bookService.getbyid(Id));
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/AllBook")
    public ResponseEntity<ServicesResponse<List<Book>>> getAllBook(){
        ServicesResponse<List<Book>> data = _bookService.getAllBook();
        if(data.Success){
         return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/book/{Id}")
    public ResponseEntity<ServicesResponse<Book>> Updatebook(@PathVariable(value = "Id") Integer Id,@RequestBody Book book ){
        return  ResponseEntity.ok(_bookService.update(Id,book));
    }




}
