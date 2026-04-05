package com.edvard.casino.controller;

import com.edvard.casino.dto.GuestAuthResponse;
import com.edvard.casino.dto.GuestLoginResult;
import com.edvard.casino.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/guest")
    public ResponseEntity<GuestAuthResponse> guestUserLogin(HttpServletResponse response) {
        GuestLoginResult result = authService.createGuestUser();

        ResponseCookie responseCookie = ResponseCookie.from("jwt", result.token())
                .httpOnly(true)
                .secure(false) // TODO: Set to true in production when using HTTPS
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofDays(1))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return ResponseEntity.ok()
                .body(GuestAuthResponse.from(result));
    }
}
