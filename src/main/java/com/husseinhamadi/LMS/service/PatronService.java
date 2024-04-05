package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.NotFoundException;

import java.util.List;

public interface PatronService {


    List<PatronDTO> getPatronList();

    Patron getPatronById(Long patronId) throws NotFoundException;

    Patron createPatron(PatronDTO patron);

    Patron updatePatron(Long patronId, PatronDTO patron) throws NotFoundException;

    String deletePatron(Long patronId) throws NotFoundException;


}
