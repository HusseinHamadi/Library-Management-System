package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepo extends JpaRepository<Patron, Long> {

}
