package com.husseinhamadi.LMS.dto;

import jakarta.validation.constraints.*;
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




}
