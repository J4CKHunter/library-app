package com.test.library.service;

import com.test.library.dto.UserDto;
import com.test.library.exception.GenericException;
import com.test.library.model.User;
import com.test.library.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordencoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordencoder) {
        this.userRepository = userRepository;
        this.passwordencoder = passwordencoder;
    }

    public UserDto createUser(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserDto.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    public UserDto getUser(String username){

        User savedUser = findUserByUsername(username);

        return UserDto.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    // username password authentication filter chain'da kullanılması için
    public User findUserByUsername(String username){
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("User not found by given id.")
                        .build());
    }

}
