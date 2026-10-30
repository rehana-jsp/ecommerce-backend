package com.jsp.E_Commerce_project.dto.user;


import lombok.Data;

@Data
public class RegisterResponseDto {


    private long id;
    private String name;
    private String email;
    private String role;
    private boolean active;
}
