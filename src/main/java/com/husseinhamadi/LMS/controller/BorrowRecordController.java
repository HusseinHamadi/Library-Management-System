package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.BorrowRecordDTO;
import com.husseinhamadi.LMS.exception.AlreadyBorrowedException;
import com.husseinhamadi.LMS.exception.BookNotBorrowedException;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowRecordController {
    @Autowired
    BorrowRecordService borrowRecordService;


    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordDTO> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) throws NotFoundException, AlreadyBorrowedException {
        return new ResponseEntity<BorrowRecordDTO>(BorrowRecordDTO.toDTO(borrowRecordService.borrowBook(bookId, patronId)), HttpStatus.OK);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordDTO> updatePatron(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) throws NotFoundException, BookNotBorrowedException {
        return new ResponseEntity<BorrowRecordDTO>(BorrowRecordDTO.toDTO(borrowRecordService.returnBook(bookId, patronId)), HttpStatus.OK);
    }


}
