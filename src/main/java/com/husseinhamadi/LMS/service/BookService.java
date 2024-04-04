package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;

import java.util.List;

public interface BookService {

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    List<BookDTO> getBookList();

    BookDTO getBookById(Long bookId) throws NotFoundException;

    BookDTO createBook(BookDTO book);

    BookDTO updateBook(Long bookId, BookDTO book) throws NotFoundException;

    String deleteBook(Long bookId) throws NotFoundException;


}
