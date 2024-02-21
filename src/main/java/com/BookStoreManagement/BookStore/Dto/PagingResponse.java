package com.BookStoreManagement.BookStore.Dto;

public class PagingResponse<T> {
    public int TotalPage;
    public int CurrentPage;
    public int ElementsInCurrentPage;
    public T Data;
}
