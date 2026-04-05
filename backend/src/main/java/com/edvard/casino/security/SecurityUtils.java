package com.edvard.casino.security;

import com.edvard.casino.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Utility class for extracting the currently authenticated user from the security context.
 */
@Component
public class SecurityUtils {

    /**
     * Get the currently authenticated user from the security context.
     *
     * @return the authenticated User object
     * @throws IllegalStateException if no user is authenticated
     */
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }

        throw new IllegalStateException("Principal is not a User object");
    }

    /**
     * Get the currently authenticated user's ID.
     *
     * @return the authenticated user's UUID
     * @throws IllegalStateException if no user is authenticated
     */
    public static UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * Check if a user is currently authenticated.
     *
     * @return true if a user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof User;
    }
}

