package com.lammai.SpringBootBase.controller;

import com.lammai.SpringBootBase.dto.auth.LoginDto;
import com.lammai.SpringBootBase.dto.auth.LoginResponse;
import com.lammai.SpringBootBase.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        LoginResponse loginResponse = authService.login(loginDto);
        System.out.println(loginDto);
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserResponseDto> register(@RequestBody CreateUserDto createUserDto) {
//        UserResponseDto user = userService.creatUser(createUserDto);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
}
