package com.BookStoreManagement.BookStore.Repository;

import com.BookStoreManagement.BookStore.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleIgnoreCase(String title);

    Book findByIsbn(String isbn);
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
}
