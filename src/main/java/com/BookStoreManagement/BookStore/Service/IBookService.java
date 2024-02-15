package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    ServicesResponse<List<Book>> getAllBook();
    ServicesResponse<String> AddBook(Book book);
    ServicesResponse<Optional<Book>> delete(Integer id);
    ServicesResponse<Optional<Book>> getbyid(Integer id);
     ServicesResponse<Book> update(Integer id, Book book);
}
