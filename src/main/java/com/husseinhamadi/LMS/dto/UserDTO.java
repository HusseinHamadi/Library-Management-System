package com.husseinhamadi.LMS.dto;

import com.husseinhamadi.LMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDTO {

    private String username;
    private Set<String> roles;
    private String password;

    // Getters and Setters

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setRoles(dto.getRoles());
        return user;
    }
}
