package com.husseinhamadi.LMS.exception;

public class AlreadyBorrowedException extends Exception {
    public AlreadyBorrowedException(String bookAlreadyBorrowed) {
        super(bookAlreadyBorrowed);
    }
}
