package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BookRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@SpringBootTest
class BookServiceTest {


    @Autowired
    private BookService bookService;

    //creating a mock book repo
    @MockBean
    private BookRepo bookRepo;


    @BeforeEach
    void setUp() {

        Book book1 = Book.builder()
                .id(1L)
                .title("Math")
                .ISBN(123L)
                .author("Jack")
                .publicationYear(2020L)
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .title("Science")
                .ISBN(321L)
                .author("Sam")
                .publicationYear(2020L)
                .build();

        Long bookId=1L;

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        //when methods called
        Mockito.when(bookRepo.findAll())
                .thenReturn(bookList);

        Mockito.when(bookRepo.findById(bookId))
                .thenReturn(Optional.of(book1));

//        Mockito.when(bookRepo.save(book1))
//                .thenReturn(book1);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void verifyBookRepoIsCalled_WhenGetBookListIsCalled() {

        List<BookDTO> list = bookService.getBookList();
        verify(bookRepo).findAll();
    }

    @Test
    @DisplayName("Get Data based on bookId")
    void whenValidBookId_thenBookShouldReturn() throws NotFoundException {

        Long bookId=1L;

        Book foundBook = bookService.getBookById(bookId);
        assertEquals(bookId, foundBook.getId());
        assertEquals("Math", foundBook.getTitle());
        verify(bookRepo).findById(bookId);
    }


    @Test
    void shouldCreateBookAndSaveToRepository() throws NotFoundException {

        BookDTO book = BookDTO
                .build(
                        "old", "old", 111L, 2020L);
        //calling the createBook
        bookService.createBook(book);

        //creating a captor to capture argument of type book
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        //1-verify the bookRepo is invoked
        //2-capture the argument passed to the save() method
        verify(bookRepo)
                .save(bookArgumentCaptor.capture());

        //getting the value of the captured argument
        Book capturedBook = bookArgumentCaptor.getValue();

        //asserting that the captured Book is the same as the Book in the createBook
        //this means that it is calling the repo correctly and passing the correct args
        assertThat(capturedBook).isEqualTo(BookDTO.toEntity(book));
    }


    @Test
    void whenBookPresent_thenDeleteBook() throws NotFoundException {

        Long bookId =1L;
        // Call the deleteBook method from your service
        bookService.deleteBook(bookId);

        //creating a captor to capture argument of type book
        ArgumentCaptor<Book> bookArgumentCaptor =
                ArgumentCaptor.forClass(Book.class);

        //1-verify the bookRepo is invoked
        //2-capture the argument passed to the delete() method
        verify(bookRepo)
                .delete(bookArgumentCaptor.capture());

        //getting the value of the captured argument
        Book capturedBook = bookArgumentCaptor.getValue();

        //asserting that the captured Book is the same as the Book in the createBook
        //this means that it is calling the repo correctly and passing the correct args
        assertThat(capturedBook.getTitle()).isEqualTo("Math");
    }

}