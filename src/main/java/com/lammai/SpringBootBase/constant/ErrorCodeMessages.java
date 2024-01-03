package com.lammai.SpringBootBase.constant;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMessages {
    public static final Map<String, String> errorMessages = new HashMap<>();
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
    public static final String USERNAME_ALREADY_EXIST = "USERNAME_ALREADY_EXIST";
    public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";
    public static final String WRONG_USERNAME_OR_PASSWORD = "WRONG_USERNAME_OR_PASSWORD";
    public static final String ACCESS_DENIED = "ACCESS_DENIED";
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
    public static final String JWT_EXPIRED = "JWT_EXPIRED";
    public static final String JWT_INVALID = "JWT_INVALID";
    public static final String REFRESH_TOKEN_EXPIRED = "REFRESH_TOKEN_EXPIRED";
    public static final String REFRESH_TOKEN_INVALID = "REFRESH_TOKEN_INVALID";

    static {
        errorMessages.put(USER_NOT_EXIST, "User not found");
        errorMessages.put(USERNAME_ALREADY_EXIST, "Username has already existed");
        errorMessages.put(EMAIL_ALREADY_EXIST, "Email has already existed");
        errorMessages.put(WRONG_USERNAME_OR_PASSWORD, "Wrong username or password");
        errorMessages.put(ACCESS_DENIED, "Access denied");
        errorMessages.put(JWT_EXPIRED, "JWT expired");
        errorMessages.put(JWT_INVALID, "JWT invalid");
        errorMessages.put(REFRESH_TOKEN_EXPIRED, "Refresh token expired");
        errorMessages.put(REFRESH_TOKEN_INVALID, "Refresh token invalid");
    }

}
