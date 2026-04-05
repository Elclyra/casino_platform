package com.edvard.casino.service;

import com.edvard.casino.dto.GuestLoginResult;
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
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, WalletService walletService, JwtService jwtService) {
        this.walletService = walletService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public GuestLoginResult createGuestUser() {
        String username = "Guest#" + UUID.randomUUID().toString().substring(0, 8);
        User user = new User();
        user.setUsername(username);
        user.setUserType(UserType.GUEST);

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        WalletResponse savedWallet = walletService.createWallet(savedUser, BigDecimal.valueOf(1000), Currency.EUR);

        return new GuestLoginResult(savedUser.getId(), username, savedWallet.balance(), savedWallet.currency(), token);
    }
}
