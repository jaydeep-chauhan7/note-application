package com.example.notes.service.impl;

import com.example.notes.dto.UserDto;
import com.example.notes.entity.User;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        logger.info("User with email is found: {}", email);
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
