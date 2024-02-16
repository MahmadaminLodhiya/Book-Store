package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;
import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;

import java.util.List;

public interface IAuthoreService {
    ServicesResponse<String> AddAuthor(AuthorDto author);
    ServicesResponse<List<Author>> GetAllAuthor();
    ServicesResponse<Author> updateAuthor(Integer id, AuthorDto author);
    ServicesResponse<List<Book>> getAllBookOfAuthor(Integer authorId);
    ServicesResponse<AuthorDto> getAuthorById(Integer authorId);
}
