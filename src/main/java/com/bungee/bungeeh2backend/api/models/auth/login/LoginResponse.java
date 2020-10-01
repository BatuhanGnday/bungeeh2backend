package com.bungee.bungeeh2backend.api.models.auth.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private LoginResponseType type;
}
