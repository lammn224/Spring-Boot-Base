package com.lammai.SpringBootBase.service;

import com.lammai.SpringBootBase.dto.auth.AuthResponse;
import com.lammai.SpringBootBase.dto.auth.LoginDto;
import com.lammai.SpringBootBase.dto.auth.RefreshTokenDto;
import com.lammai.SpringBootBase.exception.UnauthorizedException;
import com.lammai.SpringBootBase.model.User;
import com.lammai.SpringBootBase.security.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.REFRESH_TOKEN_EXPIRED;
import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.REFRESH_TOKEN_INVALID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        var user = userService.findByUsername(loginDto.getUsername());
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(token, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        var refreshToken = refreshTokenDto.getRefreshToken();
        String username;

        try {
            username = jwtService.extractUsername(refreshToken);
            User user = userService.findByUsername(username);
            String token = jwtService.generateToken(user);

            return new AuthResponse(token, refreshToken);
        } catch (ExpiredJwtException ex) {
            throw new UnauthorizedException(REFRESH_TOKEN_EXPIRED);
        } catch (Exception ex) {
            throw new UnauthorizedException(REFRESH_TOKEN_INVALID);
        }
    }
}
