package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {
    @Autowired
    private AuthorService _authorService;
    @Autowired
    private BookService _bookService;
    @Autowired
    private AuthorRepository _authorRepository;
    Map<String, Object> MAP1;

    @BeforeEach
    void setup() {
        MAP1 = new HashMap<>();
        MAP1.put("name", "niraj");
        MAP1.put("email", "test@book.com");
    }

    @Test
    void addAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setEmail("niraj.ghetiya@gmail.com");
        authorDto.setName("niraj");
        ServicesResponse<Author> test = _authorService.AddAuthor(authorDto);
        Optional<Author> newAuthor = _authorRepository.findById(test.Data.getId());
        assertThat(newAuthor.get().getName()).isEqualTo(authorDto.getName());
        assertThat(newAuthor.get().getEmail()).isEqualTo(authorDto.getEmail());
        assertThat(test.Success).isTrue();
    }

    @Test
    void getAllAuthor() {
        ServicesResponse<List<Author>> test = _authorService.GetAllAuthor();
        assertThat(test.Success).isTrue();
        assertThat(test.Data.size()).isEqualTo(10);
    }

    @Test
    void updateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setEmail("niraj@gmail.com");
        authorDto.setName("niraj");
        ServicesResponse<Author> test = _authorService.UpdateAuthor(1, authorDto);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getEmail()).isEqualTo(authorDto.getEmail());
        assertThat(test.Data.getName()).isEqualTo(authorDto.getName());
    }

    @Test
    void getAllBookOfAuthor() {
        ServicesResponse<List<Book>> test = _authorService.GetAllBookOfAuthor(1);
        assertThat(test.Success).isTrue();
        for (Book book : test.Data) {
            assertThat(book.getAuthorId()).isEqualTo(1);
        }
    }

    @Test
    void getAuthorById() {
        ServicesResponse<AuthorDto> test = _authorService.GetAuthorById(1);
        Optional<Author> author = _authorRepository.findById(1);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getName()).isEqualTo(author.get().getName());
        assertThat(test.Data.getEmail()).isEqualTo(author.get().getEmail());

    }

    @Test
    void updateSpecificField() {
        ServicesResponse<Author> test = _authorService.UpdateSpecificField(2, MAP1);
        Optional<Author> author = _authorRepository.findById(1);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getName()).isEqualTo(MAP1.get("name"));
        assertThat(test.Data.getEmail()).isEqualTo(MAP1.get("email"));

    }
}