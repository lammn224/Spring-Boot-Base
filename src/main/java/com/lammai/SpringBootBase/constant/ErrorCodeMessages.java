package com.lammai.SpringBootBase.constant;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMessages {
    public static final Map<String, String> errorMessages = new HashMap<>();
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
    public static final String USERNAME_ALREADY_EXIST = "USERNAME_ALREADY_EXIST";
    public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";
    public static final String WRONG_USERNAME_OR_PASSWORD = "WRONG_USERNAME_OR_PASSWORD";

    static {
        errorMessages.put(USER_NOT_EXIST, "User not found");
        errorMessages.put(USERNAME_ALREADY_EXIST, "Username has already existed");
        errorMessages.put(EMAIL_ALREADY_EXIST, "Email has already existed");
        errorMessages.put(WRONG_USERNAME_OR_PASSWORD, "Wrong username or password");
    }

}
