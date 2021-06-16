package com.maha.watchstore.exception;

public class NonExistentProductException extends RuntimeException{
    public NonExistentProductException(String message) {
        super(message);
    }
}
