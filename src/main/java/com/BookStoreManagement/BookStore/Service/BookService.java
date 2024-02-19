package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AddBookDto;
import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository _Book;
    private final AuthorRepository _Author;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        _Book = bookRepository;
        _Author = authorRepository;
    }

    public ServicesResponse<List<Book>> GetAllBook() {
        ServicesResponse<List<Book>> response = new ServicesResponse<List<Book>>();
        response.Data = _Book.findAll();
        return response;
    }

    public ServicesResponse<String> AddBook(AddBookDto book) {
        ServicesResponse<String> response = new ServicesResponse<>();
        try {
            Book obj = new Book();
            obj.setAuthorId(book.getAuthorid());
            obj.setPrice(book.getPrice());
            obj.setIsbn(book.getIsbn());
            obj.setTitle(book.getTitle());
            Author author = _Author.findById(obj.getAuthorId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Author Id: " +
                            String.valueOf(obj.getAuthorId())));
            Optional<Book> IsBnBook= Optional.ofNullable(_Book.findByIsbn(obj.getIsbn()));
            if(!IsBnBook.isPresent()){
           try {
               Book newbook = _Book.save(obj);
           }catch (Exception ex){
               throw new Exception("We apologize, but we encountered an error while attempting to add the book to our database. This could be due to technical issues or invalid data provided.");
           }

            response.Data = "We are pleased to inform you that the book data has been successfully added to our database.";}else {
             throw new Exception("We regret to inform you that the ISBN you provided already exists in our database. Each ISBN must be unique to ensure accurate cataloging and inventory management.");
            }

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Optional<Book>> Delete(Integer id) {
        ServicesResponse<Optional<Book>> response = new ServicesResponse<>();
        try {
            Optional<Book> book = _Book.findById(id);
            if (book.isPresent()) {
                _Book.deleteById(id);
                response.Data = book;

            } else {
                throw new Exception("We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid. As a result, we are unable to process the deletion request.");
            }
        } catch (Exception ex) {

            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Book> Update(Integer id, AddBookDto book) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {
            Book book1 = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                            "We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid."));
            if(book.getAuthorid()!=0) {
                Author author = _Author.findById(book.getAuthorid()).orElseThrow(
                        () -> new EntityNotFoundException("We regret to inform you that the update process for the book could not be completed due to an invalid author ID. The author ID provided does not correspond to a valid author in our database."));
            }



        if(book.getPrice()!=0) book1.setPrice(book.getPrice());
        if(book.getAuthorid()!=0){
            book1.setAuthorId(book.getAuthorid());
        }
            if(book.getTitle()!=null) book1.setTitle(book.getTitle());
            if(book.getIsbn()!=null){
                book1.setIsbn(book.getIsbn());
            }

            response.Data = _Book.save(book1);


        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<BookDto> GetById(Integer id) {
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            Book book = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid."));

            Author author = _Author.findById(book.getAuthorId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Author Id: " +
                            String.valueOf(id)));

            response.Data = new BookDto();
            response.Data.setAuthor(author);
            response.Data.setPrice(book.getPrice());
            response.Data.setIsbn(book.getIsbn());
            response.Data.setTitle(book.getTitle());

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }


    public ServicesResponse<BookDto> GetByTitle(String title) {
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            List<Book> book;

                book = _Book.findByTitleIgnoreCase(title);

                Author author = _Author.findById(book.get(0).getAuthorId()).orElseThrow(
                        () -> new EntityNotFoundException("Invalid Author Id: " +
                                String.valueOf(book.get(0).getAuthorId())));

                response.Data = new BookDto();
                response.Data.setAuthor(author);
                response.Data.setPrice(book.get(0).getPrice());
                response.Data.setIsbn(book.get(0).getIsbn());
                response.Data.setTitle(book.get(0).getTitle());
        }catch (Exception e){
            response.Data = null;
            response.Success = false;
            response.Message = "We're sorry, but the book you're searching for could not be found in our database. It's possible that the title or details provided may be incorrect, or the book may not be available in our inventory.";
        }

        return response;
    }
    public ServicesResponse<Book> updateProductByFields(int id, Map<String, Object> fields) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {

            Optional<Book> existingProduct = _Book.findById(id);

            if (existingProduct.isPresent()) {
                fields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(Book.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingProduct.get(), value);
                });
                response.Data=_Book.save(existingProduct.get());
            }}catch (Exception ex){
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();

        }
        return  response;

    }
}
