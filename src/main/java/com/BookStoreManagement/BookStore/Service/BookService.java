package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository _book;

    public BookService(BookRepository bookRepository){
        _book = bookRepository;
    }

    public ServicesResponse<List<Book>> getAllBook(){
        ServicesResponse<List<Book>> response = new ServicesResponse<List<Book>>();
       response.Data=_book.findAll();
       return response;
    }

    public ServicesResponse<String> AddBook(Book book){
        ServicesResponse<String> response = new ServicesResponse<>();
        try {



            Book newbook = _book.save(book);
            response.Data= "Book add...";
        }catch (Exception ex){
          response.Data=null;
          response.Success=false;
          response.Massage =ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Optional<Book>> delete(Integer id){
        ServicesResponse<Optional<Book>> response = new ServicesResponse<>();
        try{
         Optional<Book> book = _book.findById(id);
            if(book.isPresent()){
         _book.deleteById(id);
         response.Data = book;

            }else {
                throw new Exception("Invelid Book Id");
            }
        }catch (Exception ex){

            response.Data = null;
            response.Success=false;
            response.Massage=ex.getMessage();
        }
        return response;
    }
}
