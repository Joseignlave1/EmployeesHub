package com.example.employeeshub.services;

import com.example.employeeshub.controllers.LoginRequest;
import com.example.employeeshub.controllers.RegisterRequest;
import com.example.employeeshub.controllers.TokenResponse;
import com.example.employeeshub.models.UserModel;
import com.example.employeeshub.repository.UserRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    public TokenResponse register (RegisterRequest request) {
        //Verifica si el email ya existe en la BD
        if(userService.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }

        UserModel userModel = UserModel.builder()
                .nombre(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        UserModel savedUser = userRepository.save(userModel);
        String jwtToken = jwtService.generateToken(userModel);
        String refreshToken = jwtService.generateRefreshToken(userModel);
        return new TokenResponse(jwtToken, refreshToken);
    }

    public TokenResponse login(LoginRequest loginRequest) {
        UserModel actualUser = userService.findByEmail(loginRequest.email())
                .orElseThrow(() -> new InvalidParameterException("The email is not valid"));

        //passwordEncoder.matches(plainTextPassword, hashedPassword)
        if(!passwordEncoder.matches(loginRequest.password(), actualUser.getPassword())) {
            throw new IllegalArgumentException("Incorrect Credencials");
        }

        String jwtToken = jwtService.generateToken(actualUser);
        String refreshToken = jwtService.generateRefreshToken(actualUser);
        return new TokenResponse(jwtToken, refreshToken);

    }
}
