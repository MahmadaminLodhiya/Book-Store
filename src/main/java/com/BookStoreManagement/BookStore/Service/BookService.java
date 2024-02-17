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

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository _Book;
    private final AuthorRepository _Author;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        _Book = bookRepository;
        _Author = authorRepository;
    }

    public ServicesResponse<List<Book>> getAllBook() {
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
            Book newbook = _Book.save(obj);
            response.Data = "Book add...";

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Optional<Book>> delete(Integer id) {
        ServicesResponse<Optional<Book>> response = new ServicesResponse<>();
        try {
            Optional<Book> book = _Book.findById(id);
            if (book.isPresent()) {
                _Book.deleteById(id);
                response.Data = book;

            } else {
                throw new Exception("Invelid Book Id");
            }
        } catch (Exception ex) {

            response.Data = null;
            response.Success = false;
            response.Massage = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Book> update(Integer id, Book book) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {
            Book book1 = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                            "Invalid id: " + String.valueOf(id)));
            Author author = _Author.findById(book.getAuthorId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Author Id: " +
                            String.valueOf(id)));

            List<Book> book3 = _Book.findByIsbn(book.getIsbn());


            book1.setPrice(book.getPrice());
            book1.setAuthorId(book.getAuthorId());
            book1.setTitle(book.getTitle());
            book1.setIsbn(book.getIsbn());
            response.Data = _Book.save(book1);


        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<BookDto> getbyid(Integer id) {
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            Book book = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Id: " +
                            String.valueOf(id)));

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
            response.Massage = ex.getMessage();
        }
        return response;
    }


    public ServicesResponse<BookDto> getbytital(String tital) {
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            List<Book> book = _Book.findByTitle(tital);
            if (book != null) {

                Author author = _Author.findById(book.get(0).getAuthorId()).orElseThrow(
                        () -> new EntityNotFoundException("Invalid Author Id: " +
                                String.valueOf(book.get(0).getAuthorId())));

                response.Data = new BookDto();
                response.Data.setAuthor(author);
                response.Data.setPrice(book.get(0).getPrice());
                response.Data.setIsbn(book.get(0).getIsbn());
                response.Data.setTitle(book.get(0).getTitle());
            } else {
                throw new Exception("Invalid Id");
            }

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = ex.getMessage();
        }
        return response;
    }
}
