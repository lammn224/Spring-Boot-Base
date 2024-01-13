package com.lammai.SpringBootBase.security;

import com.lammai.SpringBootBase.exception.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.JWT_EXPIRED;
import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.JWT_INVALID;
import static com.lammai.SpringBootBase.constant.SecurityConstant.AUTH_HEADER;
import static com.lammai.SpringBootBase.constant.SecurityConstant.TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    protected static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        ContentCachingRequestWrapper contentCachingRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponse = new ContentCachingResponseWrapper(response);

        if (request.getRequestURI().contains("/auth/login")) {
            filterChain.doFilter(contentCachingRequest, contentCachingResponse);
            loggingRequest(request, contentCachingRequest);
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
            request.setAttribute("username", username);
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
        filterChain.doFilter(contentCachingRequest, contentCachingResponse);
        loggingRequest(request, contentCachingRequest);
        loggingResponse(request, response);
    }

    private void loggingRequest(HttpServletRequest request, ContentCachingRequestWrapper contentCachingRequest) {

        try {
            String requestBody = getStringValue(contentCachingRequest.getContentAsByteArray(), contentCachingRequest.getCharacterEncoding());
            JSONParser parser = new JSONParser();
            JSONObject requestBodyJson = (JSONObject) parser.parse(requestBody);
            if ("GET".equalsIgnoreCase(contentCachingRequest.getMethod())) {
                requestBody = contentCachingRequest.getParameterMap().entrySet().stream().map(stringEntry -> stringEntry.getKey() + ":" + Arrays.toString(stringEntry.getValue())).collect(Collectors.joining(","));
            }
            if ("POST".equalsIgnoreCase(contentCachingRequest.getMethod())) {
                if (!requestBody.isEmpty()) {
                    if (requestBodyJson.containsKey("password")) requestBodyJson.put("password", "******");
                }
            }
            requestBody = requestBodyJson.toJSONString();
            logger.info("Received request: {} {} with request body {} from {}", request.getMethod(), request.getRequestURI(), requestBody, request.getRemoteAddr());

        } catch (IllegalStateException e) {
            logger.debug(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void loggingResponse(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Sent response: {} {} with status {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
