package com.jsp.E_Commerce_project.service;

import com.jsp.E_Commerce_project.dto.user.LoginRequestDto;
import com.jsp.E_Commerce_project.dto.user.LoginResponseDto;
import com.jsp.E_Commerce_project.dto.user.RegisterRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    ResponseEntity<RegisterRequestDto> registerUser(RegisterRequestDto registerRequestDto);

    public ResponseEntity<LoginResponseDto> userLoginByEmail( LoginRequestDto loginRequestDto) ;
    public ResponseEntity<LoginResponseDto> userLoginByPhone( LoginRequestDto loginRequestDto) ;
}
