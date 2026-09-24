package com.backend.service.wallet_transaction_service.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private UserService userService;

    @Transactional
    public void transfer(Long senderId, Long receiverId, Double amount) {
        userService.debit(senderId, amount);
        userService.credit(receiverId, amount);
    }
}