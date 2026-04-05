package com.edvard.casino.dto;

import com.edvard.casino.type.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public record GuestLoginResult(
        UUID userId,
        String username,
        BigDecimal balance,
        Currency currency,
        String token
) { }
