package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.BorrowRecord;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.AlreadyBorrowedException;
import com.husseinhamadi.LMS.exception.BookNotBorrowedException;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BorrowRecordRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BorrowRecordServiceTest {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @MockBean
    private BorrowRecordRepo borrowRecordRepo;
    @MockBean
    private BookService bookService;
    @MockBean
    private PatronService patronService;


    @Test
    void whenBorrowingBookWithReturnDateOrNotBorrowedBefore_thenCreateNewBorrowing() throws AlreadyBorrowedException, NotFoundException {

        Patron patron1 = Patron.builder()
                .id(1L)
                .name("Alice")
                .contactInfo("alice@example.com")
                .build();

        Book book1 = Book.builder()
                .id(1L)
                .title("Math")
                .ISBN(123L)
                .author("Jack")
                .publicationYear(2020L)
                .build();

        Date borrowDate = new Date(2015);
        Date returnDate = new Date(2019);

        BorrowRecord borrowRecord1 =
                BorrowRecord.builder()
                        .id(1L)
                        .borrowDate(borrowDate)
                        .patron(patron1)
                        .book(book1)
                        .returnDate(returnDate)
                        .build();

        Mockito.when(borrowRecordRepo.findNotReturnedBook(1L, 1L))
                .thenReturn(Optional.empty());

        //mockito.any() to match any instance of class borrowRecord
        Mockito.when(borrowRecordRepo.save(Mockito.any(BorrowRecord.class)))
                .thenReturn(borrowRecord1);

        Mockito.when(patronService.getPatronById(1L))
                .thenReturn(patron1);
        Mockito.when(bookService.getBookById(1L))
                .thenReturn(book1);

        BorrowRecord br=borrowRecordService.borrowBook(1L,1L);

        assertThat(br.getId()).isNotNull();
        assertThat(br.getBook().getTitle()).isEqualTo("Math");

    }


    @Test
    void whenBookIsBorrowed_thenUpdateReturnDate() throws BookNotBorrowedException, NotFoundException {

        Patron patron1 = Patron.builder()
                .id(1L)
                .name("Alice")
                .contactInfo("alice@example.com")
                .build();

        Book book1 = Book.builder()
                .id(1L)
                .title("Math")
                .ISBN(123L)
                .author("Jack")
                .publicationYear(2020L)
                .build();

        Date borrowDate = new Date(2015);
        Date returnDate = new Date(2019);

        BorrowRecord borrowRecord1 =
                BorrowRecord.builder()
                        .id(1L)
                        .borrowDate(borrowDate)
                        .patron(patron1)
                        .book(book1)
                        .returnDate(returnDate)
                        .build();

        Mockito.when(borrowRecordRepo.findNotReturnedBook(1L, 1L))
                .thenReturn(Optional.ofNullable(borrowRecord1));

        //mockito.any() to match any instance of class borrowRecord
        Mockito.when(borrowRecordRepo.save(Mockito.any(BorrowRecord.class)))
                .thenReturn(borrowRecord1);

        Mockito.when(patronService.getPatronById(1L))
                .thenReturn(patron1);
        Mockito.when(bookService.getBookById(1L))
                .thenReturn(book1);

        BorrowRecord br=borrowRecordService.returnBook(1L,1L);

        assertThat(br).isNotNull();
        assertThat(br.getPatron().getName()).isEqualTo("Alice");
    }
}