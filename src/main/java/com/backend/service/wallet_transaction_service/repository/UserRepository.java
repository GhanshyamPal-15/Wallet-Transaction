package com.backend.service.wallet_transaction_service.repository;
import com.backend.service.wallet_transaction_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
