package com.example.employeeshub.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse (
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
){

}