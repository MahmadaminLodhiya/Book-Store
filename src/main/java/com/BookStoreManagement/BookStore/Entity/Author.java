package com.BookStoreManagement.BookStore.Entity;

import com.BookStoreManagement.BookStore.Dto.AuthorDto;

import javax.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    @Column(unique = true)
    private String email;
    public Author(){}
    public Author(AuthorDto author)
    {
        name = author.getName();
        email = author.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
