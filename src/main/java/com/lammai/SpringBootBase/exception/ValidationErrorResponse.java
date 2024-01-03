package com.lammai.SpringBootBase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private String code;
    private List<String> messages;
}
