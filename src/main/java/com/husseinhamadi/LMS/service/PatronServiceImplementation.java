package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.PatronRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronServiceImplementation implements PatronService {

    @Autowired
    PatronRepo patronRepo;

    @Override
    public PatronDTO toDTO(Patron patron) {
        return PatronDTO.build(patron.getName(), patron.getContactInfo());
    }

    @Override
    public Patron toEntity(PatronDTO patronDTO) {
        return Patron.builder()
                .name(patronDTO.getName())
                .contactInfo(patronDTO.getContactInfo())
                .build();
    }

    @Override
    public List<PatronDTO> getPatronList() {

        List<Patron> patrons= patronRepo.findAll();
        return patrons
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatronDTO getPatronById(Long patronId) throws NotFoundException {
        Optional<Patron> patronOpt=patronRepo.findById(patronId);
        if(patronOpt.isPresent()){
            return toDTO(patronOpt.get());
        }
        else {
            throw new NotFoundException("patron of id: "+patronId+" doesn't exist");
        }
    }

    @Override
    public PatronDTO createPatron(PatronDTO patron) {

        return toDTO(patronRepo.save(toEntity(patron)));
    }

    @Override
    public PatronDTO updatePatron(Long patronId, PatronDTO patronDTO) throws NotFoundException {

            Optional<Patron> patronOpt=patronRepo.findById(patronId);

            if(patronOpt.isPresent()){
                Patron patron = patronOpt.get();
                if(Objects.nonNull(patronDTO.getName()) && !"".equalsIgnoreCase((patronDTO.getName()))){
                    patron.setName(patronDTO.getName());
                }

                if(Objects.nonNull(patronDTO.getContactInfo()) && !"".equalsIgnoreCase((patronDTO.getContactInfo()))){
                    patron.setContactInfo(patron.getContactInfo());
                }


                patronRepo.save(patron);
                return toDTO(patron);
            }
            else{
                throw new NotFoundException("patron of id: "+patronId+" doesn't exist");
            }

    }

    @Override
    public String deletePatron(Long patronId) throws NotFoundException {
        Optional<Patron> patronOpt=patronRepo.findById(patronId);
        if(patronOpt.isPresent()){
            patronRepo.delete(patronOpt.get());
            return "patron Deleted with ID: "+patronId;
        }
        else {
            throw new NotFoundException("patron of id: "+patronId+" doesn't exist");
        }
    }
}
