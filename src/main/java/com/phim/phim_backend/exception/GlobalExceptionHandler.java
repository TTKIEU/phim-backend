package com.phim.phim_backend.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler (PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePostNotFound(PostNotFoundException ex){
        return Map.of("error",ex.getMessage());
    }

    @ExceptionHandler (IdempotencyConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> handleIdempotencyConflict(IdempotencyConflictException ex){
        return Map.of("error",ex.getMessage());
    }
}