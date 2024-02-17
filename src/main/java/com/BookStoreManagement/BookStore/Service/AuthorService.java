package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements IAuthoreService {
    private final AuthorRepository _author;
    @Autowired
    private final BookRepository _books;

    public AuthorService(AuthorRepository author, BookRepository books) {
        _author = author;
        _books = books;
    }

    public ServicesResponse<String> AddAuthor(AuthorDto authorDto) {
        ServicesResponse<String> response = new ServicesResponse<>();
        try {
            List<Author> authors = _author.findByEmail(authorDto.getEmail());
            if (authors.size() != 0) {
                throw new IllegalArgumentException("Email already available!!");
            }
            Author author1 = new Author(authorDto);
            _author.save(author1);
            response.Data = authorDto.getName() + " Author Added!";

        } catch (Exception e) {
            response.Data = null;
            response.Success = false;
            response.Massage = e.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<List<Author>> GetAllAuthor() {
        ServicesResponse<List<Author>> response = new ServicesResponse<>();

        try {
            response.Data = _author.findAll();
        } catch (Exception e) {
            response.Data = null;
            response.Success = false;
            response.Massage = e.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<Author> updateAuthor(Integer id, AuthorDto author) {
        ServicesResponse<Author> response = new ServicesResponse<>();
        try {
            Author author1 = _author.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                            String.valueOf(id)));

            author1.setEmail(author.getEmail());
            author1.setName(author.getName());
            response.Data = _author.save(author1);

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = "Invalid id " + ex.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<List<Book>> getAllBookOfAuthor(Integer authorId) {
        ServicesResponse<List<Book>> response = new ServicesResponse<>();
        try {
            Author author = _author.findById(authorId).orElseThrow(() -> new EntityNotFoundException(
                    String.valueOf(authorId)));

            List<Book> allAuthorBook = new ArrayList<>();
            List<Book> allBooks = _books.findAll();
            for (int i = 0; i < allBooks.size(); i++) {
                if (allBooks.get(i).getAuthorId() == authorId)
                    allAuthorBook.add(allBooks.get(i));
            }
            response.Data = allAuthorBook;
            response.Success = true;
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = "Invalid id " + ex.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<AuthorDto> getAuthorById(Integer authorId) {
        ServicesResponse<AuthorDto> response = new ServicesResponse<>();
        try {
            Author author = _author.findById(authorId).orElseThrow(() -> new EntityNotFoundException(
                    String.valueOf(authorId)));

            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(author.getName());
            authorDto.setEmail(author.getEmail());
            response.Data = authorDto;
            response.Success = true;
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Massage = "Invalid id " + ex.getMessage();
        }
        return response;
    }
}
