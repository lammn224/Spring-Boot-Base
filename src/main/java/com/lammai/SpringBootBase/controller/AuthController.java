package com.lammai.SpringBootBase.controller;

import com.lammai.SpringBootBase.dto.auth.AuthResponse;
import com.lammai.SpringBootBase.dto.auth.LoginDto;
import com.lammai.SpringBootBase.dto.auth.RefreshTokenDto;
import com.lammai.SpringBootBase.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        AuthResponse authResponse = authService.login(loginDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody @Valid RefreshTokenDto refreshTokenDto) {
        AuthResponse authResponse = authService.refreshToken(refreshTokenDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserResponseDto> register(@RequestBody CreateUserDto createUserDto) {
//        UserResponseDto user = userService.creatUser(createUserDto);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
}
