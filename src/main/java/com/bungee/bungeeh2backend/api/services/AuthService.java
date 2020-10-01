package com.bungee.bungeeh2backend.api.services;

import com.bungee.bungeeh2backend.api.security.jwt.JwtUtil;
import com.bungee.bungeeh2backend.api.models.auth.login.LoginRequest;
import com.bungee.bungeeh2backend.api.models.auth.login.LoginResponse;
import com.bungee.bungeeh2backend.api.models.auth.login.LoginResponseType;
import com.bungee.bungeeh2backend.api.models.auth.signup.SignUpRequest;
import com.bungee.bungeeh2backend.api.models.auth.signup.SignUpResponse;
import com.bungee.bungeeh2backend.api.models.auth.signup.SignUpResponseType;
import com.bungee.bungeeh2backend.database.models.UserProfile;
import com.bungee.bungeeh2backend.database.models.users.credential.BungeeUserDetails;
import com.bungee.bungeeh2backend.database.repositories.IUserCredentialRepository;
import com.bungee.bungeeh2backend.database.repositories.IUserProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private JwtUtil jwtUtil;
    private final IUserCredentialRepository userCredentialRepository;
    private final IUserProfileRepository userProfileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(
            JwtUtil jwtUtil,
            IUserCredentialRepository userCredentialRepository,
            IUserProfileRepository userProfileRepository, BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.jwtUtil = jwtUtil;
        this.userCredentialRepository = userCredentialRepository;
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        BungeeUserDetails bungeeUserDetails
                = userCredentialRepository.findByUsername(loginRequest.getUsername());

        if (bungeeUserDetails == null) {
            return new LoginResponse(null, LoginResponseType.INVALID_USERNAME);
        }
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), bungeeUserDetails.getPassword())) {
            return new LoginResponse(null, LoginResponseType.PASSWORD_FAIL);
        }
        return new LoginResponse(jwtUtil.generateToken(bungeeUserDetails), LoginResponseType.SUCCESS);
    }

    public SignUpResponse signup(SignUpRequest request) {
        if (userCredentialRepository.existsByUsername(request.getUsername())) {
            return new SignUpResponse(SignUpResponseType.USERNAME_ALREADY_EXIST);
        }

        BungeeUserDetails bungeeUserDetails =
                new BungeeUserDetails(
                        request.getUsername(),
                        bCryptPasswordEncoder.encode(request.getPassword())
                );

        UserProfile profile = new UserProfile(bungeeUserDetails);
        userProfileRepository.save(profile);

        return new SignUpResponse(SignUpResponseType.SUCCESS);
    }
}
