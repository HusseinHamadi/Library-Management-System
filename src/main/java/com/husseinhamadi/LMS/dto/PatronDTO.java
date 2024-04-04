package com.husseinhamadi.LMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class PatronDTO {

    @NotEmpty
    @NotBlank
    @NotNull
    private String name;

    @NotEmpty
    @NotBlank
    @NotNull
    private String contactInfo;

}
