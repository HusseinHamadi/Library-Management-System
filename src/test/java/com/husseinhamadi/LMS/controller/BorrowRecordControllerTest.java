package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.BorrowRecordDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.AlreadyBorrowedException;
import com.husseinhamadi.LMS.exception.BookNotBorrowedException;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.BorrowRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BorrowRecordController.class)
class BorrowRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowRecordService borrowRecordService;



    @BeforeEach
    void setUp() throws NotFoundException, AlreadyBorrowedException, BookNotBorrowedException {
        BorrowRecordDTO borrowRecordDTO = BorrowRecordDTO.build(new Book(), new Patron(), new Date(), new Date());

        Mockito.when(borrowRecordService.borrowBook(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(BorrowRecordDTO.toEntity(borrowRecordDTO));

        when(borrowRecordService.returnBook(any(Long.class), any(Long.class)))
                .thenReturn(BorrowRecordDTO.toEntity(borrowRecordDTO));
    }

    @Test
    void testBorrowBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testReturnBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}