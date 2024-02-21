package com.BookStoreManagement.BookStore.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
public class Book {

    public int getId() {
        return id;
    }
    public int publishedYear;

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
    public Book(){}
    public Book(int publishedYear, String title, int authorId, String isbn, int price) {
        this.publishedYear = publishedYear;
        this.title = title;
        AuthorId = authorId;
        this.isbn = isbn;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }

    private  int AuthorId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @Column(unique = true)
    private String isbn;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

}
