package com.example.notes.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String error) {
        super(error);
    }
}
