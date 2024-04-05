package com.husseinhamadi.LMS.dto;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.BorrowRecord;
import com.husseinhamadi.LMS.entity.Patron;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class BorrowRecordDTO {

    @NotEmpty
    @NotBlank
    @NotNull
    private Book book;

    @NotEmpty
    @NotBlank
    @NotNull
    private Patron patron;

    @Temporal(TemporalType.DATE)
    @NotEmpty
    @NotBlank
    @NotNull
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public static BorrowRecordDTO toDTO(BorrowRecord borrow) {
        return BorrowRecordDTO.build(borrow.getBook(), borrow.getPatron(), borrow.getBorrowDate(), borrow.getReturnDate());
    }

    public static BorrowRecord toEntity(BorrowRecordDTO borrowDTO) {
        return BorrowRecord.builder()
                .book(borrowDTO.getBook())
                .patron(borrowDTO.getPatron())
                .borrowDate(borrowDTO.getBorrowDate())
                .returnDate(borrowDTO.getReturnDate())
                .build();
    }
}

