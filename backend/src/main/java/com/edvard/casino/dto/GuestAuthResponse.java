package com.edvard.casino.dto;

import com.edvard.casino.type.Currency;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public record GuestAuthResponse(
        UUID userId,
        String username,
        BigDecimal balance,
        Currency currency
) {
    public static @Nullable GuestAuthResponse from(GuestLoginResult result) {
        return new GuestAuthResponse(result.userId(), result.username(), result.balance(), result.currency());
    }
}
