package com.edvard.casino.controller;

import com.edvard.casino.dto.DepositRequest;
import com.edvard.casino.dto.WalletResponse;
import com.edvard.casino.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{userId}/deposit")
    public WalletResponse depositBalance(@PathVariable UUID userId, @RequestBody DepositRequest request) {
        return walletService.depositBalance(userId, request.amount());
    }
}
