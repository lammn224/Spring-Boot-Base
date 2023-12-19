package com.lammai.SpringBootBase.constant;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMessages {
    public static final Map<String, String> errorMessages = new HashMap<>();

    static {
        errorMessages.put("USER_NOT_EXIST", "User not found");
    }

}
