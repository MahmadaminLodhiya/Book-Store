package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository _book;
    private final AuthorRepository _Author;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        _book = bookRepository;
        _Author = authorRepository;
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
    public ServicesResponse<Book> update(Integer id, Book book){
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {
            Book book1 = _book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                            String.valueOf(id)));


            book1.setPrice(book.getPrice());
            book1.setAuthorId(book.getAuthorId());
            book1.setTitle(book.getTitle());
            book1.setIsbn(book.getIsbn());
            response.Data = _book.save(book1);


        }catch (Exception ex){
            response.Data = null;
            response.Success=false;
            response.Massage="Invalid id " +ex.getMessage();
        }
        return  response;
    }

    public ServicesResponse<BookDto> getbyid(Integer id){
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            Book book = _book.findById(id).orElseThrow(
                    ()-> new EntityNotFoundException("Invalid Id"+
                            String.valueOf(id)));

            Author author = _Author.findById(book.getAuthorId()).orElseThrow(
                        () -> new EntityNotFoundException("Invalid Author Id"+
                                String.valueOf(id)));

                response.Data= new BookDto();
                response.Data.setAuthor(author);
                response.Data.setPrice(book.getPrice());
                response.Data.setIsbn(book.getIsbn());
                response.Data.setTitle(book.getTitle());

            }
        catch (Exception ex){
            response.Data = null;
            response.Success=false;
            response.Massage=ex.getMessage();
        }
        return  response;
    }
}
