package com.example.minipost.http.api;

import com.example.minipost.core.exception.AuthenticationException;
import com.example.minipost.core.exception.EntityNotFoundException;
import com.example.minipost.core.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.timestamp = LocalDateTime.now();
        response.error = "Invalid Request";
        response.message = e.getMessage();
        response.path = request.getDescription(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.timestamp = LocalDateTime.now();
        response.error = "Authentication Failed";
        response.message = e.getMessage();
        response.path = request.getDescription(false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.timestamp = LocalDateTime.now();
        response.error = "Entity Not Found";
        response.message = e.getMessage();
        response.path = request.getDescription(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.timestamp = LocalDateTime.now();
        response.error = "Internal Server Error";
        response.message = e.getMessage();
        response.path = request.getDescription(false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
