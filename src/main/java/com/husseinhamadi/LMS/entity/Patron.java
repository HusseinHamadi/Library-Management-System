package com.husseinhamadi.LMS.entity;

import com.husseinhamadi.LMS.dto.PatronDTO;
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
public class Patron {
    @Id
    @SequenceGenerator(
            name = "patron_sequence",
            sequenceName = "patron_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patron_sequence"
    )
    @Column(name = "patron_id")
    private Long id;


    @Column(name = "name")
    @NotEmpty
    @NotBlank
    @NotNull
    private String name;

    @Column(name = "contact_info")
    @NotEmpty
    @NotBlank
    @NotNull
    private String contactInfo;



}
