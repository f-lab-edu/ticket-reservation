package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByMerchantUid(String impUid);
}
