package com.edvard.casino.service;

import com.edvard.casino.model.User;
import com.edvard.casino.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class JwtServiceTests {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService("12345678901234567890123456789012", 3600);
    }

    @Test
    void shouldGenerateValidTokenForUser() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .username("testuser")
                .userType(UserType.GUEST)
                .build();

        String token = jwtService.generateToken(user);
        assert jwtService.isTokenValid(token);
        assert jwtService.extractUserId(token).equals(user.getId().toString());
        assert jwtService.extractUserType(token).equals(user.getUserType().name());
    }
}
