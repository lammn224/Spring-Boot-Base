package com.lammai.SpringBootBase.security;

import com.lammai.SpringBootBase.exception.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.JWT_EXPIRED;
import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.JWT_INVALID;
import static com.lammai.SpringBootBase.constant.SecurityConstant.AUTH_HEADER;
import static com.lammai.SpringBootBase.constant.SecurityConstant.TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getRequestURI().contains("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(AUTH_HEADER);
        String token;
        String username;

        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.replace(TOKEN_PREFIX, StringUtils.EMPTY);

        try {
            username = jwtService.extractUsername(token);
        } catch (ExpiredJwtException ex) {
            throw new UnauthorizedException(JWT_EXPIRED);
        } catch (Exception ex) {
            throw new UnauthorizedException(JWT_INVALID);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
