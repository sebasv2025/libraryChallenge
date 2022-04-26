package com.challenge.library.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }
}
