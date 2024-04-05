package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepo extends JpaRepository<BorrowRecord, Long> {

    Optional<List<BorrowRecord>> findByPatronIdAndBookId(Long patronId, Long BookId);

    @Query(nativeQuery = true, value = "Select * from borrow_record where patron_id=:patronId and book_id=:bookId and return_date IS NULL;")
    Optional<BorrowRecord> findNotReturnedBook(@Param("patronId") Long patronId,
                                               @Param("bookId") Long bookId);
}
