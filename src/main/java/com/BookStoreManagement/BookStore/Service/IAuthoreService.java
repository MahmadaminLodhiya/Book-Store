package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;

import java.util.List;
import java.util.Map;

public interface IAuthoreService {
    ServicesResponse<String> AddAuthor(AuthorDto author);

    ServicesResponse<List<Author>> GetAllAuthor();

    ServicesResponse<Author> UpdateAuthor(Integer id, AuthorDto author);

    ServicesResponse<List<Book>> GetAllBookOfAuthor(Integer authorId);

    ServicesResponse<AuthorDto> GetAuthorById(Integer authorId);

    ServicesResponse<Author> UpdateSpecificField(Integer authorId, Map<String, Object> fields);
}
