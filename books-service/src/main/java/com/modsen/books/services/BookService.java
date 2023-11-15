package com.modsen.books.services;

import com.modsen.books.dto.BookRequest;
import com.modsen.books.dto.BookResponse;

public interface BookService extends AbstractService<Long, BookRequest, BookResponse>{
    BookResponse findByIsbn(String isbn);
    void setToken(String token);
}
