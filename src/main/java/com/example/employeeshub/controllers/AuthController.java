package com.example.employeeshub.controllers;

import com.example.employeeshub.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody final RegisterRequest registerRequest) {
        final TokenResponse token = authService.register(registerRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        final TokenResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

}
