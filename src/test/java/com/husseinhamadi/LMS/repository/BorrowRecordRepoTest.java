package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.BorrowRecord;
import com.husseinhamadi.LMS.entity.Patron;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class BorrowRecordRepoTest {

    @Autowired
    BorrowRecordRepo borrowRecordRepo;
    
    @AfterEach
    void tearDown() {
        borrowRecordRepo.deleteAll();
    }

    @Test
    void WhenReturnDateIsNull_ReturnTheBook() {

        Book book = new Book(null, "Test Book", "Test Author", 2022L, 123456789L);
        Patron patron = new Patron(null, "test", "123");

        BorrowRecord borrow = new BorrowRecord(null, book, patron, new Date(), null);

        var saved=borrowRecordRepo.save(borrow);

        Long patronId = saved.getPatron().getId();
        Long bookId = saved.getBook().getId();

        assertThat(borrowRecordRepo.findNotReturnedBook(patronId, bookId)).isNotEmpty();

    }

    @Test
    void saveBorrow() {

        BorrowRecord borrow = new BorrowRecord(null, null, null, new Date(), null);
        borrowRecordRepo.save(borrow);
        Long id= borrow.getId();
        assertThat(id).isNotNull();
    }

    @Test
    void findAllBorrows() {

        BorrowRecord borrow1 = new BorrowRecord(null, null, null, new Date(), null);
        BorrowRecord borrow2 = new BorrowRecord(null, null, null, new Date(), null);


        borrowRecordRepo.save(borrow1);
        borrowRecordRepo.save(borrow2);

        List<BorrowRecord> allBorrows = borrowRecordRepo.findAll();

        assertThat(allBorrows.size()).isEqualTo(2);
    }

    @Test
    void deleteBorrow() {

        Book book = new Book(null, "Test Book", "Test Author", 2022L, 123456789L);
        Patron patron = new Patron(null, "test", "123");

        BorrowRecord borrow = new BorrowRecord(null, book, patron, null, null);


        Long saved= borrowRecordRepo.save(borrow).getId();


        borrowRecordRepo.deleteById(saved);

        assertThat(borrowRecordRepo.findById(saved)).isEmpty();

    }
}