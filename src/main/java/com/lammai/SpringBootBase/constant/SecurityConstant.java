package com.lammai.SpringBootBase.constant;

public class SecurityConstant {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final Long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;
    public static final Long REFRESH_EXPIRATION_TIME = 5 * 24 * 60 * 60 * 1000L;
    public static final String AUTH_HEADER = "Authorization";
    public static final String REGISTRATION_REQUEST_URI = "/auth/register";
    public static final String LOGIN_REQUEST_URI = "/auth/login";

    public static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/auth/**",
            "/users/**"
    };
}
