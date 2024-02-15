package com.BookStoreManagement.BookStore.Repository;

import com.BookStoreManagement.BookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
