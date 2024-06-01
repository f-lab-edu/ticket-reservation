package com.ticketing.solution.adapter.port.out.persistence.jpa;

import com.ticketing.solution.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
