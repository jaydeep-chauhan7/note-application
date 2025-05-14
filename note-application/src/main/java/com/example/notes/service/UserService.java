package com.example.notes.service;

import com.example.notes.dto.UserDto;
import com.example.notes.exception.UserNotFoundException;

public interface UserService {
    UserDto findByEmail(String email) throws UserNotFoundException;
}
