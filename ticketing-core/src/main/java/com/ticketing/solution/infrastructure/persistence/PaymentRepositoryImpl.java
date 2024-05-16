package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.pay.PaymentRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
}
