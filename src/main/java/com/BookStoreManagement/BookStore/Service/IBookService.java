package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.*;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.enums.OrderBy;
import com.BookStoreManagement.BookStore.enums.SortBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBookService {

    ServicesResponse<PagingResponse<List<Book>>> GetAllBook(Integer pageNumber , Integer pageSize, SortBy sortBy, OrderBy orderBy);
    ServicesResponse<Book> AddBook(AddBookDto book);
    ServicesResponse<Optional<Book>> Delete(Integer id);
    ServicesResponse<BookDto>GetById(Integer id);
    ServicesResponse<PagingResponse<List<Book>>> SearchBook(String title, Integer pageNumber, Integer pageSize, SortBy sortBy, OrderBy orderBy, Integer minPrice, Integer maxPrice,Integer minYear, Integer maxYear);

    ServicesResponse<Book> Update(Integer id, AddBookDto book);
    ServicesResponse<Book> updateProductByFields(int id, Map<String, Object> fields);
}
