package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.BorrowRecord;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.AlreadyBorrowedException;
import com.husseinhamadi.LMS.exception.BookNotBorrowedException;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.BorrowRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BorrowRecordServiceImplementation implements BorrowRecordService {

    @Autowired
    BorrowRecordRepo borrowRecordRepo;

    @Autowired
    BookService bookService;

    @Autowired
    PatronService patronService;


    @Override
    public BorrowRecord borrowBook(Long bookId, Long patronId) throws NotFoundException, AlreadyBorrowedException {

        //getting borrowed but not returned book
        Optional<BorrowRecord> borrowRecordOpt = borrowRecordRepo.findNotReturnedBook(patronId, bookId);

        //if there is not a borrowed book with a not returned date, then either book is never borrowed
        //or the book is borrowed and returned in the past
        //create new record in both cases
        if (borrowRecordOpt.isEmpty()) {
            Book book= bookService.getBookById(bookId);
            Patron patron = patronService.getPatronById(patronId);

            BorrowRecord br =borrowRecordRepo.save(new BorrowRecord(null,
                    book,
                    patron,
                    new Date(),
                    null));

            return br;
        } else {

            //the book is borrowed because the return date is not present, throw exception
            throw new AlreadyBorrowedException("Book with ID: " + bookId + " is already borrowed by Patron with ID: " + patronId);

        }

    }

    @Override
    public BorrowRecord returnBook(Long bookId, Long patronId) throws NotFoundException, BookNotBorrowedException {

        //getting borrowed but not returned book
        Optional<BorrowRecord> borrowRecordOpt = borrowRecordRepo.findNotReturnedBook(patronId, bookId);

        //if there is a record then update return date to return the book
        if (borrowRecordOpt.isPresent()) {
            BorrowRecord bR = borrowRecordOpt.get();
            bR.setReturnDate(new Date());

            return borrowRecordRepo.save(bR);
        } else {
            //if there is no record, this means either the book is never borrowed,
            //or the book is returned in the past
            //either way we cannot return the book, throw exception
            throw new BookNotBorrowedException("Book with ID: " + bookId + " is not borrowed by Patron with ID: " + patronId);
        }

    }
}
