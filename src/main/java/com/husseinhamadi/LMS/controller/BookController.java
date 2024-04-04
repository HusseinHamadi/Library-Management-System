package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBookList(){
        return new ResponseEntity<List<BookDTO>>(bookService.getBookList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) throws NotFoundException {
        return new ResponseEntity<BookDTO>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO book){
        System.out.println(book);
        return new ResponseEntity<BookDTO>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long bookId, @RequestBody BookDTO book) throws NotFoundException {
        return new ResponseEntity<BookDTO>(bookService.updateBook(bookId, book), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return new ResponseEntity<String>(bookService.deleteBook(bookId), HttpStatus.OK);
    }
}
