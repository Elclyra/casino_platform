package com.edvard.casino.service;


import com.edvard.casino.dto.WalletResponse;
import com.edvard.casino.model.User;
import com.edvard.casino.model.Wallet;
import com.edvard.casino.repository.WalletRepository;
import com.edvard.casino.type.Currency;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    public WalletResponse createWallet(User user, BigDecimal balance, Currency currency) {
        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setBalance(balance);
        wallet.setCurrency(currency);

        Wallet savedWallet = walletRepository.save(wallet);

        return new WalletResponse(savedWallet.getBalance(), savedWallet.getCurrency());
    }
}
