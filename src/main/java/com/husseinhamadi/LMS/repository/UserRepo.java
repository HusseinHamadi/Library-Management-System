package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
