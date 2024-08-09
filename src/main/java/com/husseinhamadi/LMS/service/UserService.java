package com.husseinhamadi.LMS.service;

import com.husseinhamadi.LMS.dto.UserDTO;
import com.husseinhamadi.LMS.entity.User;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<UserDTO> getAdminList() {
        List<User> admins = userRepository.findAll();
        List<UserDTO> adminDTOs = new ArrayList<>();

        for (User admin : admins) {
            adminDTOs.add(UserDTO.toDTO(admin));
        }

        return adminDTOs;
    }

    public UserDTO getAdminById(Long adminId) throws NotFoundException {
        Optional<User> adminOptional = userRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            return UserDTO.toDTO(adminOptional.get());
        } else {
            throw new NotFoundException("Admin not found with id: " + adminId);
        }
    }

    public UserDTO createAdmin(UserDTO userDTO) {
        User admin = UserDTO.toEntity(userDTO);
        admin.setPassword(encoder.encode(userDTO.getPassword()));
        User savedAdmin = userRepository.save(admin);
        return UserDTO.toDTO(savedAdmin);
    }

    public UserDTO updateAdmin(Long adminId, UserDTO userDTO) throws NotFoundException {
        Optional<User> adminOptional = userRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            User admin = adminOptional.get();
            admin.setUsername(userDTO.getUsername());
            if (userDTO.getPassword() != null) {
                admin.setPassword(encoder.encode(userDTO.getPassword()));
            }
            User updatedAdmin = userRepository.save(admin);
            return UserDTO.toDTO(updatedAdmin);
        } else {
            throw new NotFoundException("Admin not found with id: " + adminId);
        }
    }

    public String deleteAdmin(Long adminId) throws NotFoundException {
        Optional<User> adminOptional = userRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            userRepository.delete(adminOptional.get());
            return "Admin deleted successfully";
        } else {
            throw new NotFoundException("Admin not found with id: " + adminId);
        }
    }
}