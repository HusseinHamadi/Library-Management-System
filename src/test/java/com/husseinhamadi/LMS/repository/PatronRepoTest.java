package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Patron;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PatronRepoTest {

    @Autowired
    PatronRepo patronRepo;
    
    
    @AfterEach
    void tearDown() {
        patronRepo.deleteAll();
    }

    @Test
    void savePatron() {
        Patron patron = new Patron(null, "test", "123");


        patronRepo.save(patron);
        Long id= patron.getId();
        assertThat(id).isNotNull();
    }

    @Test
    void findAllPatrons() {
        Patron patron1 = new Patron(null, "test", "123");
        Patron patron2 = new Patron(null, "test", "123");

        patronRepo.save(patron1);
        patronRepo.save(patron2);

        List<Patron> allPatrons = patronRepo.findAll();

        assertThat(allPatrons.size()).isEqualTo(2);
    }

    @Test
    void deletePatron() {
        Patron patron = new Patron(null, "test", "123");


        Long saved=patronRepo.save(patron).getId();


        patronRepo.deleteById(saved);

        assertThat(patronRepo.findById(saved)).isEmpty();

    }
}