package com.bungee.bungeeh2backend.api.security.filters;

import com.bungee.bungeeh2backend.api.security.authentication.UserCredentialService;
import com.bungee.bungeeh2backend.api.security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityClassLoad;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserCredentialService userCredentialService;

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserCredentialService userCredentialService, JwtUtil jwtUtil) {
        this.userCredentialService = userCredentialService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader;
            log.debug("Token before trim" + token);
            // token = authorizationHeader.substring(7);
            token = token.substring(7);
            log.debug("Token after trim" + token);
            username = jwtUtil.extractUsername(token);
            log.debug(username);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userCredentialService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(httpServletRequest)
                );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                usernamePasswordAuthenticationToken
                        );
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
