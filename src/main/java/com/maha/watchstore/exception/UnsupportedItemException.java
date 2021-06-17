package com.maha.watchstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedItemException extends RuntimeException {
    public UnsupportedItemException(String message) {
        super(message);
    }
}
