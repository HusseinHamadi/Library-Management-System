package com.husseinhamadi.LMS.dto;

import com.husseinhamadi.LMS.entity.Patron;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public static PatronDTO toDTO(Patron patron) {
        return PatronDTO.build(patron.getName(), patron.getContactInfo());
    }


    public static Patron toEntity(PatronDTO patronDTO) {
        return Patron.builder()
                .name(patronDTO.getName())
                .contactInfo(patronDTO.getContactInfo())
                .build();
    }
}
