package com.lammai.SpringBootBase.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
    private String code;
    public BadRequestException(String code, String message) {
        super(message);
        this.code = code;
    }
}