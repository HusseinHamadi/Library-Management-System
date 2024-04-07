package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Mock
    private BookRepo bookRepo;
    private AutoCloseable autoCloseable;



    @BeforeEach
    void setUp() {


    }

    @Test
    void getBookList() {
        int size=bookRepo.findAll().size();
        assertEquals(2, size);
    }

    @Test
    @DisplayName("Get Data based on bookId")
    void whenValidBookId_thenBookShouldReturn() throws NotFoundException {
        Long bookId = 1L;
        Book found =
                bookService.getBookById(bookId);
        assertEquals(bookId, found.getId());
    }



    @Test
    void updateBook() throws NotFoundException {
        Long bookId = 1L;
        BookDTO book = BookDTO
                    .build(
                    "updated","updated", 111L, null);

        Book updatedBook = bookService.updateBook(bookId, book);

        assertEquals(book.getTitle(), updatedBook.getTitle());
    }

    @Test
    void deleteBook() {
        Book book = Book.builder()
                    .title("Math")
                    .ISBN(123L)
                    .author("Jack")
                    .id(1L)
                    .build();



    }
}