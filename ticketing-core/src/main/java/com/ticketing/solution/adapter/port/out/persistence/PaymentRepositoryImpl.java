package com.ticketing.solution.adapter.port.out.persistence;

import com.ticketing.solution.domain.pay.PaymentRepository;
import com.ticketing.solution.adapter.port.out.persistence.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
}
