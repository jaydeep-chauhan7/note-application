package com.example.notes.service;

import com.example.notes.dto.UserDto;
import com.example.notes.exception.UserNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByEmail(String email) throws UserNotFoundException;
}
