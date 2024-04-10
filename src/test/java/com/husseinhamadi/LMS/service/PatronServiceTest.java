package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.PatronRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PatronServiceTest {

    @Autowired
    private PatronService patronService;

    @MockBean
    private PatronRepo patronRepo;

    @BeforeEach
    void setUp() {

        Patron patron1 = Patron.builder()
                .id(1L)
                .name("Alice")
                .contactInfo("alice@example.com")
                .build();

        Patron patron2 = Patron.builder()
                .id(2L)
                .name("Bob")
                .contactInfo("bob@example.com")
                .build();

        Long patronId=1L;

        List<Patron> patronList = new ArrayList<>();

        patronList.add(patron1);
        patronList.add(patron2);

        Mockito.when(patronRepo.findAll())
                .thenReturn(patronList);

        Mockito.when(patronRepo.findById(patronId))
                .thenReturn(Optional.of(patron1));

        Mockito.when(patronRepo.save(patron2))
                .thenReturn(patron1);

    }



    @Test
    void verifyPatronRepoIsCalled_WhenGetPatronListIsCalled() {

        List<PatronDTO> list = patronService.getPatronList();
        verify(patronRepo).findAll();
    }

    @Test
    @DisplayName("Get Data based on patronId")
    void whenValidPatronId_thenPatronShouldReturn() throws NotFoundException {

        Long patronId=1L;

        Patron foundPatron = patronService.getPatronById(patronId);
        assertEquals(patronId, foundPatron.getId());
        assertEquals("Alice", foundPatron.getName());
        verify(patronRepo).findById(patronId);
    }


    @Test
    void shouldCreatePatronAndSaveToRepository() throws NotFoundException {

        PatronDTO patron = PatronDTO
                .build(
                        "old", "123");
        //calling the createPatron
        patronService.createPatron(patron);

        //creating a captor to capture argument of type patron
        ArgumentCaptor<Patron> patronArgumentCaptor =
                ArgumentCaptor.forClass(Patron.class);

        //1-verify the patronRepo is invoked
        //2-capture the argument passed to the save() method
        verify(patronRepo)
                .save(patronArgumentCaptor.capture());

        //getting the value of the captured argument
        Patron capturedPatron = patronArgumentCaptor.getValue();

        //asserting that the captured Patron is the same as the Patron in the createPatron
        //this means that it is calling the repo correctly and passing the correct args
        assertThat(capturedPatron).isEqualTo(PatronDTO.toEntity(patron));
    }


    @Test
    void whenPatronPresent_thenDeletePatron() throws NotFoundException {

        Long patronId =1L;
        // Call the deletePatron method from your service
        patronService.deletePatron(patronId);

        //creating a captor to capture argument of type patron
        ArgumentCaptor<Patron> patronArgumentCaptor =
                ArgumentCaptor.forClass(Patron.class);

        //1-verify the patronRepo is invoked
        //2-capture the argument passed to the delete() method
        verify(patronRepo)
                .delete(patronArgumentCaptor.capture());

        //getting the value of the captured argument
        Patron capturedPatron = patronArgumentCaptor.getValue();

        //asserting that the captured Patron is the same as the Patron in the createPatron
        //this means that it is calling the repo correctly and passing the correct args
        assertThat(capturedPatron.getName()).isEqualTo("Alice");
    }
}