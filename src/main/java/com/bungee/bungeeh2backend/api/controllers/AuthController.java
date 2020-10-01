package com.bungee.bungeeh2backend.api.controllers;

import com.bungee.bungeeh2backend.api.models.auth.login.LoginRequest;
import com.bungee.bungeeh2backend.api.models.auth.login.LoginResponse;
import com.bungee.bungeeh2backend.api.models.auth.signup.SignUpRequest;
import com.bungee.bungeeh2backend.api.models.auth.signup.SignUpResponse;
import com.bungee.bungeeh2backend.api.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.accepted()
                .body(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.accepted().body(authService.signup(request));
    }
}
