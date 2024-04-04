package com.husseinhamadi.LMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "book_id")
    private Long id;


    @Column(name = "title")
    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @Column(name = "author")
    @NotEmpty
    @NotBlank
    @NotNull
    private String author;

    @Column(name = "publication_year")
    @NotNull
    private Long publicationYear;

    @Column(name = "ISBN")
    @NotNull
    private Long ISBN;
}
