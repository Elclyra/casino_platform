package com.edvard.casino.dto;

import com.edvard.casino.type.Currency;

import java.math.BigDecimal;

public record WalletResponse(
        BigDecimal balance,
        Currency currency
) {}
