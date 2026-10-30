package com.jsp.E_Commerce_project.service.imp;

import com.jsp.E_Commerce_project.dto.user.LoginRequestDto;
import com.jsp.E_Commerce_project.dto.user.LoginResponseDto;
import com.jsp.E_Commerce_project.dto.user.RegisterRequestDto;
import com.jsp.E_Commerce_project.enums.Role;
import com.jsp.E_Commerce_project.exception.InvalidEmailException;
import com.jsp.E_Commerce_project.exception.InvalidPhoneNumberException;
import com.jsp.E_Commerce_project.exception.InvalidRoleException;
import com.jsp.E_Commerce_project.model.User;
import com.jsp.E_Commerce_project.repository.UserRepository;
import com.jsp.E_Commerce_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    @Override
    public ResponseEntity<RegisterRequestDto> registerUser(RegisterRequestDto registerRequestDto) {
       if ( userRepository.existsByEmail(registerRequestDto.getEmail())){
           throw new InvalidEmailException("invalid email");
       }
       if ( userRepository.existsByPhone(registerRequestDto.getPhone())){
           throw new IllegalArgumentException();
       }
       User user=objectMapper.convertValue(registerRequestDto,User.class);

       if(registerRequestDto.getRole().toUpperCase().equals("CUSTOMER")){
           user.setRole(Role.CUSTOMER);
       }
       else if(registerRequestDto.getRole().toUpperCase().equals("SELLER")){
           user.setRole(Role.SELLER);
       }
       else if(registerRequestDto.getRole().toUpperCase().equals("ADMIN")){
           user.setRole(Role.ADMIN);
       }else {
           throw new InvalidRoleException("invalid role ");
       }

       user=userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).
                body(objectMapper.convertValue(user,RegisterRequestDto.class));
    }

    @Override
    public ResponseEntity<LoginResponseDto> userLoginByEmail(LoginRequestDto loginRequestDto) {
       User user= userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                ()-> new InvalidEmailException("invalid email")
        );
       LoginResponseDto loginResponseDto=new  LoginResponseDto();
       loginResponseDto.setUsername(user.getName());
       loginResponseDto.setMessage("user logged in successfully");
       loginResponseDto.setRole(user.getRole());
        return ResponseEntity.ok(loginResponseDto);
    }

    @Override
    public ResponseEntity<LoginResponseDto> userLoginByPhone(LoginRequestDto loginRequestDto) {
        User user= userRepository.findByPhone(loginRequestDto.getPhone()).orElseThrow(
                ()->new InvalidPhoneNumberException("invalid phone number")
        );
        LoginResponseDto loginResponseDto=new  LoginResponseDto();
        loginResponseDto.setUsername(user.getName());
        loginResponseDto.setMessage("user logged in successfully");
        loginResponseDto.setRole(user.getRole());
        return ResponseEntity.ok(loginResponseDto);
    }
}
