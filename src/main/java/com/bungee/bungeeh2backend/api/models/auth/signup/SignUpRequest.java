package com.bungee.bungeeh2backend.api.models.auth.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequest {
    private String username;
    private String password;
}
