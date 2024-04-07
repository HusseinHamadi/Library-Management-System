package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.entity.BorrowRecord;
import com.husseinhamadi.LMS.exception.BookNotBorrowedException;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.exception.AlreadyBorrowedException;

public interface BorrowRecordService {


    BorrowRecord borrowBook(Long bookId, Long patronId) throws NotFoundException, AlreadyBorrowedException;

    BorrowRecord returnBook(Long bookId, Long patronId) throws NotFoundException, BookNotBorrowedException;

}
