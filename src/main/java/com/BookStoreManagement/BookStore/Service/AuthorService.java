package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            if (!authors.isEmpty()) {
                throw new IllegalArgumentException("Email already available!!");
            }
            Author author1 = new Author(authorDto);
            _author.save(author1);
            response.Data = authorDto.getName() + " Author Added!";

        } catch (Exception e) {
            response.Data = null;
            response.Success = false;
            response.Message = e.getMessage();
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
            response.Message = e.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<Author> UpdateAuthor(Integer id, AuthorDto author) {
        ServicesResponse<Author> response = new ServicesResponse<>();
        try {
            Author author1 = _author.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Invalid id " +
                            String.valueOf(id)));

            author1.setEmail(author.getEmail());
            author1.setName(author.getName());
            response.Data = _author.save(author1);

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<List<Book>> GetAllBookOfAuthor(Integer authorId) {
        ServicesResponse<List<Book>> response = new ServicesResponse<>();
        try {
            Author author = _author.findById(authorId).orElseThrow(() -> new EntityNotFoundException(
                    "Invalid id: " +  String.valueOf(authorId)));

            List<Book> allAuthorBook = new ArrayList<>();
            List<Book> allBooks = _books.findAll();
            for (Book book:allBooks) {
                if (book.getAuthorId() == authorId)
                    allAuthorBook.add(book);
            }
            response.Data = allAuthorBook;
            response.Success = true;
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<AuthorDto> GetAuthorById(Integer authorId) {
        ServicesResponse<AuthorDto> response = new ServicesResponse<>();
        try {
            Author author = _author.findById(authorId).orElseThrow(() -> new EntityNotFoundException(
                    "Invalid id " +  String.valueOf(authorId)));

            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(author.getName());
            authorDto.setEmail(author.getEmail());
            response.Data = authorDto;
            response.Success = true;
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message =ex.getMessage();
        }
        return response;
    }

    @Override
    public ServicesResponse<Author> UpdateSpecificField(Integer authorId, Map<String, Object> fields) {
        ServicesResponse<Author> response = new ServicesResponse<>();
        try {
            Author existingAuthor = _author.findById(authorId).orElseThrow(
                    () -> new EntityNotFoundException(
                            "Invalid id " +  String.valueOf(authorId)));

            fields.forEach((key, value)->{
                Field field = ReflectionUtils.findField(Author.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingAuthor, value);
            });
            _author.save(existingAuthor);
            response.Data = existingAuthor;
            response.Success = true;
        }catch (Exception ex){
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }
}
