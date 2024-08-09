package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.UserDTO;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAdminList() {
        List<UserDTO> adminList = userService.getAdminList();
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getAdminById(@PathVariable("id") Long adminId) throws NotFoundException {
        UserDTO adminDTO = userService.getAdminById(adminId);
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createAdmin(@RequestBody @Valid UserDTO userDTO) {
        UserDTO createdAdmin = userService.createAdmin(userDTO);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateAdmin(@PathVariable("id") Long adminId, @RequestBody UserDTO userDTO) throws NotFoundException {
        UserDTO updatedAdmin = userService.updateAdmin(adminId, userDTO);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long adminId) throws NotFoundException {
        String responseMessage = userService.deleteAdmin(adminId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
