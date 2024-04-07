package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {


    @Query(nativeQuery = true, value = "Select * from borrow_record where patron_id=:patronId and book_id=:bookId and return_date IS NULL;")
    Optional<BorrowRecord> findNotReturnedBook(@Param("patronId") Long patronId,
                                               @Param("bookId") Long bookId);
}
