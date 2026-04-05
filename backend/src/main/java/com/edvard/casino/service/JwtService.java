package com.edvard.casino.service;

import com.edvard.casino.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final int accessTokenExpires;

    public JwtService(
            @Value("${jwt.secretKey}") String accessTokenSecret,
            @Value("${jwt.accessTokenExpires}") int accessTokenExpires
    ) {
        this.secretKey = Keys.hmacShaKeyFor(accessTokenSecret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpires = accessTokenExpires;
    }

    public String generateToken(User user) {
        Date now = new Date();
        long expiresIn = this.accessTokenExpires * 1000L * 60;

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("userType", user.getUserType().name())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiresIn))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token){
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUserId(String token){
        return extractAllClaims(token).getSubject();
    }

    public String extractUserType(String token){
        return extractAllClaims(token).get("userType", String.class);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
