package com.edvard.casino.controller;

import com.edvard.casino.dto.WalletResponse;
import com.edvard.casino.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final WalletService walletService;

    public TransactionController(WalletService walletService) {
        this.walletService = walletService;
    }
}
