package com.lammai.SpringBootBase.service;

import com.lammai.SpringBootBase.dto.auth.LoginDto;
import com.lammai.SpringBootBase.dto.auth.LoginResponse;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public LoginResponse login(LoginDto loginDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        var user = userService.findByUsername(loginDto.getUsername());
        String token = jwtService.generateToken(user);
        System.out.println(loginDto.getPassword());
        System.out.println(loginDto.getUsername());

        return new LoginResponse(token);
    }
}
