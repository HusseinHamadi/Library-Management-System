package com.husseinhamadi.LMS.repository;

import com.husseinhamadi.LMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRolesContaining(String role);
}
