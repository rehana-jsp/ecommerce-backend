package com.jsp.E_Commerce_project.dto.user;

import com.jsp.E_Commerce_project.enums.Role;
import lombok.Data;


@Data
public class LoginResponseDto {

    private String token;
    private Role role;
    private String username;
    private String message;
}
