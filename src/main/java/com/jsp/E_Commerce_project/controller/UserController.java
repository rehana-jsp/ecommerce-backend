package com.jsp.E_Commerce_project.controller;


import com.jsp.E_Commerce_project.dto.user.LoginRequestDto;
import com.jsp.E_Commerce_project.dto.user.LoginResponseDto;
import com.jsp.E_Commerce_project.dto.user.RegisterRequestDto;
import com.jsp.E_Commerce_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterRequestDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
       return  userService.registerUser(registerRequestDto);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequestDto) {
        if(loginRequestDto.getEmail()!=null){
          return   userService.userLoginByEmail(loginRequestDto);
        }
        else if(loginRequestDto.getPhone()!=null){
          return   userService.userLoginByPhone(loginRequestDto);
        }
        else{
            throw new RuntimeException("Invalid email or phone number");
        }

    }

}
