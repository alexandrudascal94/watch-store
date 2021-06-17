package com.maha.watchstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonExistingProductException extends RuntimeException{
    public NonExistingProductException(String message) {
        super(message);
    }
}
