package com.edvard.casino.service;

import com.edvard.casino.dto.GuestAuthResponse;
import com.edvard.casino.dto.WalletResponse;
import com.edvard.casino.model.User;
import com.edvard.casino.repository.UserRepository;
import com.edvard.casino.type.Currency;
import com.edvard.casino.type.UserType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AuthService {
    private WalletService walletService;
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository, WalletService walletService) {
        this.walletService = walletService;
        this.userRepository = userRepository;
    }

    @Transactional
    public GuestAuthResponse createGuestUser() {
        String username = "Guest#" + UUID.randomUUID().toString().substring(0, 8);
        User user = new User();
        user.setUsername(username);
        user.setUserType(UserType.GUEST);

        User savedUser = userRepository.save(user);

        WalletResponse savedWallet = walletService.createWallet(savedUser, BigDecimal.valueOf(1000), Currency.EUR);

        return new GuestAuthResponse(savedUser.getId(), username, savedWallet.balance(), savedWallet.currency());
    }
}
