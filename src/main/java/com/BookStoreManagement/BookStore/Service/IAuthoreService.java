package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;

import java.util.List;

public interface IAuthoreService {
    ServicesResponse<String> AddAuthor(String authorName);
    ServicesResponse<List<Author>> GetAllAuthor();
}
