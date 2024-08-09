package com.husseinhamadi.LMS.config;

import com.husseinhamadi.LMS.entity.User;
import com.husseinhamadi.LMS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitialAdminSetup {

    @Autowired
    private UserRepo userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Bean
    public CommandLineRunner createInitialAdmin() {
        return args -> {
            if (userRepository.findByUsername("admin")==null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));

                userRepository.save(admin);
                System.out.println("Initial admin user created with username: admin and password: admin123");
            }
        };
    }
}
