package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getPatronList() {
        return new ResponseEntity<List<PatronDTO>>(patronService.getPatronList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable("id") Long patronId) throws NotFoundException {
        return new ResponseEntity<PatronDTO>(PatronDTO.toDTO(patronService.getPatronById(patronId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatronDTO> createPatron(@RequestBody @Valid PatronDTO patron) {
        return new ResponseEntity<PatronDTO>(PatronDTO.toDTO(patronService.createPatron(patron)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable("id") Long patronId, @RequestBody PatronDTO patron) throws NotFoundException {
        return new ResponseEntity<PatronDTO>(PatronDTO.toDTO(patronService.updatePatron(patronId, patron)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable("id") Long patronId) throws NotFoundException {
        return new ResponseEntity<String>(patronService.deletePatron(patronId), HttpStatus.OK);
    }
}
