package com.BookStoreManagement.BookStore.Repository;

import com.BookStoreManagement.BookStore.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByEmail(String email);
}
