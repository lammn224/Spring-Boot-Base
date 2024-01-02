package com.lammai.SpringBootBase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.WRONG_USERNAME_OR_PASSWORD;
import static com.lammai.SpringBootBase.constant.ErrorCodeMessages.errorMessages;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorMessages.get(ex.getMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorMessages.get(ex.getMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
