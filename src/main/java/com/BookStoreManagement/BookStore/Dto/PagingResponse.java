package com.BookStoreManagement.BookStore.Dto;

public class PagingResponse<T> {
    public int TotalPage;
    public int CurrentPage;
    public T Data;
}
