package com.BookStoreManagement.BookStore.Repository;

import com.BookStoreManagement.BookStore.Entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
@Autowired
  private BookRepository _bookRepository;

    @Test
    void findByTitleIgnoreCase() {
        Optional<Book> book = _bookRepository.findById(4);
        String title = book.get().getTitle().toUpperCase();
        List<Book> test = _bookRepository.findByTitleIgnoreCase(title);
        assertThat(test.get(0).getTitle()).isEqualTo(book.get().getTitle());
        assertThat(test.get(0).getId()).isEqualTo(book.get().getId());
    }

    @Test
    void findByIsbn() {
        Optional<Book> book = _bookRepository.findById(4);
        Book test = _bookRepository.findByIsbn(book.get().getIsbn());
        assertThat(test.getIsbn()).isEqualTo(book.get().getIsbn());
    }


}