package com.example.employeeshub.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest (
        @NotNull
        @NotEmpty(message = "The email should not be empty")
        @Email(message = "The email needs to have a valid format")
        String email,
        @NotNull
        @NotEmpty(message = "The password should not be empty")
        @Size(min = 6, message = "The password should have at least 6 characters")
        String password
){
}
