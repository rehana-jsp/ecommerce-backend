package com.jsp.E_Commerce_project.dto.user;


import com.jsp.E_Commerce_project.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private boolean active;
}
