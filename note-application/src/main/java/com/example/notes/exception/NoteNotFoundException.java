package com.example.notes.exception;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException(String error) {
        super(error);
    }
}
