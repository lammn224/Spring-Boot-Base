package com.lammai.SpringBootBase.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}