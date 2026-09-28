package com.phim.phim_backend.exception;

public class IdempotencyConflictException extends RuntimeException{
    public IdempotencyConflictException(){
        super("Request is modified");
    }
    
}
