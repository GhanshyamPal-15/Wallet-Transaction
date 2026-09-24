package com.backend.service.wallet_transaction_service.service;


import com.backend.service.wallet_transaction_service.entity.User;
import com.backend.service.wallet_transaction_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void debit(Long userId, Double amount) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBalance(user.getBalance()-amount);
        userRepository.save(user);
    }

    public void credit(Long userId, Double amount) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBalance(user.getBalance()+amount);
        userRepository.save(user);
    }
}