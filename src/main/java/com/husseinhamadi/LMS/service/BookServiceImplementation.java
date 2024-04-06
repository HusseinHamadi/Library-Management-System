package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

    @Autowired
    BookRepo bookRepo;


    @Override
    public List<BookDTO> getBookList() {

        List<Book> books = bookRepo.findAll();
        return books
                .stream()
                .map(BookDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(Long bookId) throws NotFoundException {
        Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            return bookOpt.get();
        } else {
            throw new NotFoundException("Book of id: " + bookId + " doesn't exist");
        }
    }

    @Override
    public Book createBook(BookDTO book) {

        return bookRepo.save(BookDTO.toEntity(book));
    }

    @Override
    public Book updateBook(Long bookId, BookDTO bookDTO) throws NotFoundException {

        Optional<Book> bookOpt = bookRepo.findById(bookId);

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (Objects.nonNull(bookDTO.getTitle()) && !"".equalsIgnoreCase((bookDTO.getTitle()))) {
                book.setTitle(bookDTO.getTitle());
            }

            if (Objects.nonNull(bookDTO.getAuthor()) && !"".equalsIgnoreCase((bookDTO.getAuthor()))) {
                book.setAuthor(book.getAuthor());
            }

            if (Objects.nonNull(bookDTO.getPublicationYear())) {
                book.setPublicationYear(bookDTO.getPublicationYear());
            }

            if (Objects.nonNull(bookDTO.getISBN())) {
                book.setISBN(bookDTO.getISBN());
            }

            return bookRepo.save(book);
        } else {
            throw new NotFoundException("Book of id: " + bookId + " doesn't exist");
        }

    }

    @Override
    public String deleteBook(Long bookId) throws NotFoundException {
        Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            bookRepo.delete(bookOpt.get());
            return "Book Deleted with ID: " + bookId;
        } else {
            throw new NotFoundException("Book of id: " + bookId + " doesn't exist");
        }
    }
}
