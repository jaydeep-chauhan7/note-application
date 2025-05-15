package com.example.notes.security;

import com.example.notes.dto.UserDto;
import com.example.notes.exception.UserNotFoundException;
import com.example.notes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("loadUserByUsername: Trying to load user with email {}", email);
        try {
            Optional<UserDto> existingUser = userService.findByEmail(email);
            if (existingUser.isPresent()) {
                UserDto userDto = existingUser.get();
                return User.builder()
                        .username(userDto.getEmail())
                        .password(userDto.getPassword())
                        .authorities("USERS")
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
        } catch (UserNotFoundException e) {
            logger.error("loadUserByUsername: User not found with email {}", email);
            throw new UsernameNotFoundException("User not found with email: " + email, e);
        }
    }
}
