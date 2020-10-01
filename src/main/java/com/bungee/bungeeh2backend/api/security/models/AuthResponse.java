package com.bungee.bungeeh2backend.api.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthResponse {
    private String token;
    private AuthResponseType type;
}
