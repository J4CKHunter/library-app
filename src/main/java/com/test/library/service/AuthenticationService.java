package com.test.library.service;

import com.test.library.dto.TokenResponseDto;
import com.test.library.exception.GenericException;
import com.test.library.request.LoginRequest;
import com.test.library.utils.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService,
                                 TokenGenerator tokenGenerator,
                                 AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(authentication))
                    .userDto(userService.getUser(loginRequest.getUsername()))
                    .build();
        }catch (Exception e){
            throw new GenericException().builder()
                    .errorMessage("User not found")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }

    }
}
