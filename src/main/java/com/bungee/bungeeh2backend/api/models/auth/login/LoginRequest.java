package com.bungee.bungeeh2backend.api.models.auth.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
