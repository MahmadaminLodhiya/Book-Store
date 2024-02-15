package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.ServicesResponse;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthoreService {
    private final AuthorRepository _author;

    public AuthorService(AuthorRepository author)
    {
        _author = author;
    }
    public ServicesResponse<String> AddAuthor(String authorName)
    {
        ServicesResponse<String> response = new ServicesResponse<>();
        try{
            Author author = new Author(authorName);
            _author.save(author);
            response.Data = authorName+" Author Added!";
        }catch (Exception e)
        {
            response.Data=null;
            response.Success=false;
            response.Massage =e.getMessage();
        }
        return response;
    }

    public ServicesResponse<List<Author>> GetAllAuthor()
    {
        ServicesResponse<List<Author>> response = new ServicesResponse<>();

        try {
            response.Data = _author.findAll();
        }
        catch (Exception e)
        {
            response.Data=null;
            response.Success=false;
            response.Massage =e.getMessage();
        }
        return response;
    }
}
