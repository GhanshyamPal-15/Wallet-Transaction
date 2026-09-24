package com.backend.service.wallet_transaction_service.controller;

import com.backend.service.wallet_transaction_service.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam Double amount) {

        try {
            service.transfer(senderId, receiverId, amount);
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
        return "Transfer completed";
    }
}