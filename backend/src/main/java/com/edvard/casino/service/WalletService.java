package com.edvard.casino.service;


import com.edvard.casino.dto.WalletResponse;
import com.edvard.casino.exception.WalletNotFoundException;
import com.edvard.casino.model.Transaction;
import com.edvard.casino.model.User;
import com.edvard.casino.model.Wallet;
import com.edvard.casino.repository.TransactionRepository;
import com.edvard.casino.repository.WalletRepository;
import com.edvard.casino.type.Currency;
import com.edvard.casino.type.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }


    public WalletResponse createWallet(User user, BigDecimal balance, Currency currency) {
        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setBalance(balance);
        wallet.setCurrency(currency);

        Wallet savedWallet = walletRepository.save(wallet);

        return new WalletResponse(savedWallet.getBalance(), savedWallet.getCurrency());
    }


    @Transactional
    public WalletResponse depositBalance(UUID userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }

        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new WalletNotFoundException("Wallet not found for user id: " + userId));
        wallet.setBalance(wallet.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);

        transactionRepository.save(transaction);

        return new WalletResponse(wallet.getBalance(), wallet.getCurrency());
    }
}
