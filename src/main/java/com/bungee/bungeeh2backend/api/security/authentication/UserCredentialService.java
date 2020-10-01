package com.bungee.bungeeh2backend.api.security.authentication;

import com.bungee.bungeeh2backend.database.models.users.credential.UserCredential;
import com.bungee.bungeeh2backend.database.repositories.IUserCredentialRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService implements UserDetailsService {

    private final IUserCredentialRepository credentialRepository;

    public UserCredentialService(IUserCredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = credentialRepository.findByUsername(username);
        if (userCredential == null) {
            throw new UsernameNotFoundException(username);
        }

        return new BungeeUserPrincipal(userCredential);
    }
}
