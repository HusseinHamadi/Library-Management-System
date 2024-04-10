package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @AfterEach
    void tearDown() {
        bookRepo.deleteAll();
    }

    @Test
    void shouldSaveBookAndGenerateId() {
        Book book = new Book(null, "Test Book", "Test Author", 2022L, 123456789L);


        bookRepo.save(book);
        Long id= book.getId();
        assertThat(id).isNotNull();
    }

    @Test
    void shouldFindAllBooks() {
        Book book1 = new Book(null, "Test Book 1", "Test Author 1", 2022L, 111111111L);
        Book book2 = new Book(null, "Test Book 2", "Test Author 2", 2023L, 222222222L);

        bookRepo.save(book1);
        bookRepo.save(book2);

        List<Book> allBooks = bookRepo.findAll();

        assertThat(allBooks.size()).isEqualTo(2);
    }

    @Test
    void shouldDeleteBookById() {
        Book book = new Book(null, "Test Book", "Test Author", 2022L, 123456789L);


        Long saved=bookRepo.save(book).getId();


        bookRepo.deleteById(saved);

        assertThat(bookRepo.findById(saved)).isEmpty();

    }
}
