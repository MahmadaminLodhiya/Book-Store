package com.BookStoreManagement.BookStore.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.BookStoreManagement.BookStore.Dto.AddBookDto;
import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.PagingResponse;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;

import com.BookStoreManagement.BookStore.enums.OrderBy;
import com.BookStoreManagement.BookStore.enums.SortBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {


    Map<String, Object> MAP1;

    Author a1;
    @Autowired
    private BookRepository _bookRepository;
    @Autowired
    private BookService _bookService;
    @Autowired
    private AuthorRepository _authorRepository;

    @BeforeEach
    void setup() {
        MAP1 = new HashMap<>();
        MAP1.put("price", 500);
        MAP1.put("title", "test");
        MAP1.put("isbn", "987-654-321");
//        MAP1.put("authorId", 2);

        a1 = new Author();


        //  _bookRepository.save(b1);
    }


    @Test
    void getAllBook() {
        ServicesResponse<PagingResponse<List<Book>>> test = _bookService.GetAllBook(1, 5, SortBy.price, OrderBy.asc);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.CurrentPage).isEqualTo(1);
        assertThat(test.Data.Data.size()).isEqualTo(5);


    }

    @Test
    void addBook() {

        AddBookDto book = new AddBookDto();
        book.setTitle("xyz");
        book.setIsbn("963.-852-741-36");
        book.setAuthorid(2);
        book.setPrice(500);

        ServicesResponse<Book> test = _bookService.AddBook(book);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getTitle()).isEqualTo(book.getTitle());
    }


    @Test
    void update() {
        AddBookDto book = new AddBookDto();
        book.setTitle("xyza");
        book.setIsbn("456.-852-741-36");
        book.setAuthorid(2);
        book.setPrice(500);
        ServicesResponse<Book> test =_bookService.Update(10,book);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getTitle()).isEqualTo(book.getTitle());
        assertThat(test.Data.getPrice()).isEqualTo(book.getPrice());
        assertThat(test.Data.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(test.Data.getAuthorId()).isEqualTo(book.getAuthorid());


    }

    @Test
    void getById() {
        Optional<Book> book = _bookRepository.findById(1);
        ServicesResponse<BookDto> test = _bookService.GetById(1);
        ServicesResponse<BookDto> Failedtest = _bookService.GetById(100);
        assertThat(Failedtest.Data).isEqualTo(null);
        assertThat(Failedtest.Success).isFalse();
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getAuthor().getId()).isEqualTo(book.get().getAuthorId());
        assertThat(test.Data.getIsbn()).isEqualTo(book.get().getIsbn());
        assertThat(test.Data.getTitle()).isEqualTo(book.get().getTitle());
        assertThat(test.Data.getPrice()).isEqualTo(book.get().getPrice());
    }


    @Test
    void updateProductByFields() {

        ServicesResponse<Book> test = _bookService.updateProductByFields(1, MAP1);
        assertThat(test.Success).isTrue();
        assertThat(test.Data.getIsbn()).isEqualTo(MAP1.get("isbn"));
        assertThat(test.Data.getTitle()).isEqualTo(MAP1.get("title"));
        assertThat(test.Data.getPrice()).isEqualTo(MAP1.get("price"));
    }

    @Test
    void delete() {

        ServicesResponse<Optional<Book>> test = _bookService.Delete(10);
        ServicesResponse<Optional<Book>> failedTest = _bookService.Delete(10);
        System.out.println("delete: " + test.Success);
        assertThat(test.Success).isTrue();
        assertThat(failedTest.Success).isFalse();
        assertThat(test.Data.get().getId()).isEqualTo(10);
        assertThat(failedTest.Message).isEqualTo("We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid. As a result, we are unable to process the deletion request.");
    }
}