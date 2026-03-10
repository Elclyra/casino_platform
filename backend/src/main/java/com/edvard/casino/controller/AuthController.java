package com.edvard.casino.controller;

import com.edvard.casino.dto.GuestAuthResponse;
import com.edvard.casino.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/guest")
    private GuestAuthResponse guestUserLogin() {
        return authService.createGuestUser();
    }

}
