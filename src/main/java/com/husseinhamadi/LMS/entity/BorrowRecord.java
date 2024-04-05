package com.husseinhamadi.LMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "borrow_record")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Separate primary key
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "borrow_date", nullable = false, updatable = false)
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;
}

