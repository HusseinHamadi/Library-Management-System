package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
