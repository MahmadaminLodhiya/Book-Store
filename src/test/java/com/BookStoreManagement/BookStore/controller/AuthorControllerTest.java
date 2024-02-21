package com.BookStoreManagement.BookStore.controller;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import com.BookStoreManagement.BookStore.ServicesResponseClass.R_Authors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @LocalServerPort
    private int port;
    private  String baseUrl="http://localhost";
    @Autowired
    private BookRepository _bookRepository;
    @Autowired
    private AuthorRepository _authorRepository;
    private static TestRestTemplate restTemplate;
    @BeforeAll
    public static void init(){
        restTemplate = new TestRestTemplate();
    }
    Map<String, Object> MAP1;
    @BeforeEach
    void setup(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/Authors");
        MAP1 = new HashMap<>();
        MAP1.put("name", "niraj");
        MAP1.put("email", "test@book.com");
    }
    @Test

    void addAuthor() {
        baseUrl = baseUrl.concat("/Author");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setEmail("niraj.ghetiya@gmail.com");
        authorDto.setName("niraj");
        ResponseEntity<ServicesResponse<Author>> test = restTemplate.exchange(baseUrl, HttpMethod.POST,null, new ParameterizedTypeReference<ServicesResponse<Author>>() { });
        assertThat(test.getBody().Success).isTrue();
    }

    @Test
    void getAllAuthor() {
        baseUrl = baseUrl.concat("/GetAllAuthor");
        ResponseEntity<ServicesResponse<List<Author>>> test = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ServicesResponse<List<Author>>>() { } );
        assertEquals(HttpStatus.OK ,test.getStatusCode());
        assertThat(test.getBody().Data.size()).isEqualTo(10);

    }

    @Test
    void getAllBooksOfAuthor() {
        baseUrl= baseUrl.concat("/GetAllBooksOfAuthor/1");
        ResponseEntity<ServicesResponse<List<Book>>> test = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ServicesResponse<List<Book>>>() {
        });
        for(Book book :test.getBody().Data){
            assertThat(book.getAuthorId()).isEqualTo(1);
        }
    }

    @Test
    void updateAuthor() {
        baseUrl = baseUrl.concat("/UpdateAuthor/1");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setEmail("niraj@gmail.com");
        authorDto.setName("niraj");
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthorDto> reEntity = new HttpEntity<>(authorDto,h);
        ResponseEntity<ServicesResponse<Author>> test = restTemplate.exchange(baseUrl, HttpMethod.PUT, reEntity, new ParameterizedTypeReference<ServicesResponse<Author>>() {
        });
        assertThat(test.getBody().Success).isTrue();
        assertThat(test.getBody().Data.getEmail()).isEqualTo(authorDto.getEmail());
        assertThat(test.getBody().Data.getName()).isEqualTo(authorDto.getName());

    }



    @Test
    void getAuthorById() {
        baseUrl = baseUrl.concat("/GetAuthorById/5");
        ResponseEntity<ServicesResponse<AuthorDto>> test = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ServicesResponse<AuthorDto>>() {
        });
        Optional<Author> author = _authorRepository.findById(5);
        assertThat(test.getBody().Success).isTrue();
        assertThat(test.getBody().Data.getName()).isEqualTo(author.get().getName());
        assertThat(test.getBody().Data.getEmail()).isEqualTo(author.get().getEmail());
    }
}