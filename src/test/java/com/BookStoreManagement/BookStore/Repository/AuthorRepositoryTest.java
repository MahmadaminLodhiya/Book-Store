package com.BookStoreManagement.BookStore.Repository;

import com.BookStoreManagement.BookStore.Entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository _authorRepository;

    @Test
    void findByEmail() {
        Optional<Author> author =  _authorRepository.findById(1);
        List<Author> test = _authorRepository.findByEmail(author.get().getEmail());
        assertThat(test.get(0).getEmail()).isEqualTo(author.get().getEmail());
        assertThat(test.get(0).getName()).isEqualTo(author.get().getName());


    }
}