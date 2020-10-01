package com.bungee.bungeeh2backend.api.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    // private static final long serialVersionUID = 2536126785479750807L;

    // private String SECRET_KEY = "3604e30e01135d5e00821287ac91586206e2a8b5a8f8cd6084eafe52648f30c618198cee62cbfc4131856b4e8520299697260ad1033f9151766499bf5bde6b6e6fe67f6db3ccf8c55068af3759eeaeea34e6c4c6cbca4fa623b2d7c633074c96b684394344a55a852abfff2c844af280ee13162d2ddc63af9f32ffca6c1ed1c9b810d765944b664552e5e56399968eb70e7a11d013d00ade89bf8c4090";
    private static final Key SECRET_KEY = MacProvider.generateKey();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        // return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getEncoded())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getEncoded())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}