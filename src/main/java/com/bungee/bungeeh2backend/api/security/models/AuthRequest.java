package com.bungee.bungeeh2backend.api.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthRequest {
    private String username;
    private String password;
}
