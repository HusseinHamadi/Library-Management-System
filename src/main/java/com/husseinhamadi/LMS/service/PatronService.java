package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.NotFoundException;

import java.util.List;

public interface PatronService {

    PatronDTO toDTO(Patron patron);

    Patron toEntity(PatronDTO patronDTO);

    List<PatronDTO> getPatronList();

    PatronDTO getPatronById(Long patronId) throws NotFoundException;

    PatronDTO createPatron(PatronDTO patron);

    PatronDTO updatePatron(Long patronId, PatronDTO patron) throws NotFoundException;

    String deletePatron(Long patronId) throws NotFoundException;


}
