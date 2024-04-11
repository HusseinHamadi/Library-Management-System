package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.PatronDTO;
import com.husseinhamadi.LMS.entity.Patron;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(PatronController.class)
class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;


    @BeforeEach
    void setUp() throws NotFoundException {
        PatronDTO patronDTO = PatronDTO.build( "Alice", "alice@example.com");
        Patron patron = new Patron(null, "Alice", "alice@example.com");


        String deletionMessage = "Patron deleted successfully";



        Mockito.when(patronService.getPatronList())
                .thenReturn(List.of(patronDTO));

        Mockito.when(patronService.getPatronById(1L))
                .thenReturn(patron);

        Mockito.when(patronService.createPatron(Mockito.any(PatronDTO.class)))
                .thenReturn(patron);

        Mockito.when(patronService.updatePatron(Mockito.anyLong(), Mockito.any(PatronDTO.class)))
                .thenReturn(patron);

        Mockito.when(patronService.deletePatron(Mockito.anyLong()))
                .thenReturn(deletionMessage);
    }

    @Test
    void testGetPatronList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetPatronById() throws Exception {
        Long patronId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/{id}", patronId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreatePatron() throws Exception {
        PatronDTO patronDTO = PatronDTO.build( "Alice", "alice@example.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"ali\",\n" +
                                "    \"contactInfo\": \"999\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdatePatron() throws Exception {
        Long patronId = 1L;
        PatronDTO updatedPatronDTO = PatronDTO.build( "Alice Updated", "alice.updated@example.com");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"ali\",\n" +
                                "    \"contactInfo\": \"999\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeletePatron() throws Exception {
        Long patronId = 1L;
        String deletionMessage = "Patron deleted successfully";

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/{id}", patronId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(deletionMessage));}
}