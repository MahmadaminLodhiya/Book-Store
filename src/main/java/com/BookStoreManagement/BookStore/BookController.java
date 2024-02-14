package com.BookStoreManagement.BookStore;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @GetMapping("/hello")
    public String hello()
    {
        return  "Hellooo";
    }
}
