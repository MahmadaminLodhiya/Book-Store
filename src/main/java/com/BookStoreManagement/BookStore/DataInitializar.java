package com.BookStoreManagement.BookStore;

import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializar implements CommandLineRunner {
    @Autowired
    private AuthorRepository _Author;
    @Autowired
    private BookRepository _Book;
    @Override
    public void run(String... args) throws Exception {

        Author a1 = new Author();
        a1.setName("Niraj");
        a1.setEmail("niraj.ghetiya@mybook.com");
        _Author.save(a1);

        Book b1 = new Book();
        b1.setTitle("xyz");
        b1.setAuthorId(1);
        b1.setIsbn("123-789-456");
        b1.setPrice(50);
        _Book.save(b1);
    }
}
