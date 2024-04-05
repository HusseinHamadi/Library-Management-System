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
    public List<PatronDTO> getPatronList() {

        List<Patron> patrons= patronRepo.findAll();
        return patrons
                .stream()
                .map(PatronDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Patron getPatronById(Long patronId) throws NotFoundException {
        Optional<Patron> patronOpt=patronRepo.findById(patronId);
        if(patronOpt.isPresent()){
            return patronOpt.get();
        }
        else {
            throw new NotFoundException("patron of id: "+patronId+" doesn't exist");
        }
    }

    @Override
    public Patron createPatron(PatronDTO patron) {

        return patronRepo.save(PatronDTO.toEntity(patron));
    }

    @Override
    public Patron updatePatron(Long patronId, PatronDTO patronDTO) throws NotFoundException {

            Optional<Patron> patronOpt=patronRepo.findById(patronId);

            if(patronOpt.isPresent()){
                Patron patron = patronOpt.get();
                if(Objects.nonNull(patronDTO.getName()) && !"".equalsIgnoreCase((patronDTO.getName()))){
                    patron.setName(patronDTO.getName());
                }

                if(Objects.nonNull(patronDTO.getContactInfo()) && !"".equalsIgnoreCase((patronDTO.getContactInfo()))){
                    patron.setContactInfo(patron.getContactInfo());
                }


                return patronRepo.save(patron);
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
