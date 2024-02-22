package com.BookStoreManagement.BookStore.controller;

import com.BookStoreManagement.BookStore.Dto.AddBookDto;
import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    @LocalServerPort
    private int port;
    private  String baseUrl="http://localhost";
    @Autowired
    private BookRepository _bookRepository;

    Map<String, Object> MAP1;
    @BeforeEach
    void setup(){
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1");
        MAP1 = new HashMap<>();
        MAP1.put("name", "niraj");
        MAP1.put("email", "test@book.com");
    }
    private static TestRestTemplate restTemplate;
    @BeforeAll
    public static void init(){
        restTemplate = new TestRestTemplate();
    }
    @Test
    void addNewBook() {
        baseUrl =baseUrl.concat("/AddBook");
        AddBookDto book = new AddBookDto();
        book.setTitle("xyz");
        book.setIsbn("963.-852-741-36");
        book.setAuthorid(2);
        book.setPrice(500);
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddBookDto> reEntity = new HttpEntity<>(book,h);
        ResponseEntity<ServicesResponse<Book>> test = restTemplate.exchange(baseUrl, HttpMethod.POST,reEntity, new ParameterizedTypeReference<ServicesResponse<Book>>() { });
        assertEquals(HttpStatus.OK ,test.getStatusCode());
        assertThat(test.getBody().Success).isTrue();
        assertThat(test.getBody().Data.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    void deleteBook() {
        baseUrl = baseUrl.concat("/Book/7");
        ResponseEntity<ServicesResponse<Optional<Book>>> test = restTemplate.exchange(baseUrl, HttpMethod.DELETE,null , new ParameterizedTypeReference<ServicesResponse<Optional<Book>>>() { });
        assertEquals(HttpStatus.OK ,test.getStatusCode());
        assertThat(test.getBody().Success).isTrue();
        assertThat(_bookRepository.findById(test.getBody().Data.get().getId()).isPresent()).isFalse();
    }

    @Test
    void bookById() {
      baseUrl = baseUrl.concat("/BookById/3");
      Optional<Book> book = _bookRepository.findById(3);
        ResponseEntity<ServicesResponse<BookDto>> test = restTemplate.exchange(baseUrl, HttpMethod.GET,null, new ParameterizedTypeReference<ServicesResponse<BookDto>>() { });
        assertEquals(HttpStatus.OK ,test.getStatusCode());
        assertThat(test.getBody().Success).isTrue();
        assertThat(test.getBody().Data.getTitle()).isEqualTo(book.get().getTitle());
        assertThat(test.getBody().Data.getAuthor().getId()).isEqualTo(book.get().getId());

    }


    @Test
    void updateBook() {
        baseUrl = baseUrl.concat("/Book/8");
        AddBookDto book = new AddBookDto();
        book.setTitle("xyza");
        book.setIsbn("456-85885-741-36");
        book.setAuthorid(2);
        book.setPrice(500);
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddBookDto> reEntity = new HttpEntity<>(book,h);
        ResponseEntity<ServicesResponse<Book>> test = restTemplate.exchange(baseUrl, HttpMethod.PUT,reEntity, new ParameterizedTypeReference<ServicesResponse<Book>>() { });
        assertEquals(HttpStatus.OK ,test.getStatusCode());
        assertThat(test.getBody().Success).isTrue();
        assertThat(test.getBody().Data.getTitle()).isEqualTo(book.getTitle());
        assertThat(test.getBody().Data.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(test.getBody().Data.getAuthorId()).isEqualTo(book.getAuthorid());
        assertThat(test.getBody().Data.getPrice()).isEqualTo(book.getPrice());


    }




}