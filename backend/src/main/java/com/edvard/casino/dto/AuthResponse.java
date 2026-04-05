package com.edvard.casino.dto;

import com.edvard.casino.type.UserType;

public record AuthResponse(
        int userId,
        UserType userType
) { }
