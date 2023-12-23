package com.lammai.SpringBootBase.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.WRONG_USERNAME_OR_PASSWORD;
import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.errorMessages;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorMessages.get(ex.getMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorMessages.get(ex.getMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        String code = WRONG_USERNAME_OR_PASSWORD;
        ErrorResponse errorResponse = new ErrorResponse(code, errorMessages.get(code));

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
