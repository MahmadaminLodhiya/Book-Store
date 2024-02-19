package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AddBookDto;
import com.BookStoreManagement.BookStore.Dto.BookDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBookService {

    ServicesResponse<List<Book>> GetAllBook();
    ServicesResponse<String> AddBook(AddBookDto book);
    ServicesResponse<Optional<Book>> Delete(Integer id);
    ServicesResponse<BookDto> GetById(Integer id);
    ServicesResponse<BookDto> GetByTital(String tital);
     ServicesResponse<Book> Update(Integer id, AddBookDto book);
    ServicesResponse<Book> updateProductByFields(int id, Map<String, Object> fields);
}
