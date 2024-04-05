package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;

import java.util.List;

public interface BookService {


    List<BookDTO> getBookList();

    Book getBookById(Long bookId) throws NotFoundException;

    Book createBook(BookDTO book);

    Book updateBook(Long bookId, BookDTO book) throws NotFoundException;

    String deleteBook(Long bookId) throws NotFoundException;


}
