package com.husseinhamadi.LMS.dto;

import com.husseinhamadi.LMS.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class BookDTO {

    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @NotEmpty
    @NotBlank
    @NotNull
    private String author;

    @NotNull
    private Long ISBN;

    @NotNull
    private Long publicationYear;

    public static BookDTO toDTO(Book book) {
        return BookDTO.build(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getISBN());
    }

    public static Book toEntity(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publicationYear(bookDTO.getPublicationYear())
                .ISBN(bookDTO.getISBN())
                .build();
    }

}
