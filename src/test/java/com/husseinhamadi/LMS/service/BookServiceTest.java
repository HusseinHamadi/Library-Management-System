package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepo bookRepo;


    @BeforeEach
    void setUp() {
        Book book=
                Book.builder()
                        .title("Math")
                        .ISBN(123L)
                        .author("Jack")
                        .id(1L)
                        .build();
        Mockito.when(bookRepo.findById(1L))
                .thenReturn(Optional.ofNullable(book));
    }

    @Test
    void getBookList() {
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
    void createBook() {

    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}