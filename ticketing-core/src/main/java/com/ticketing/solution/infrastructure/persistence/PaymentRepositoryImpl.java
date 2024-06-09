package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.PaymentRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public Payment findByMerchantUid(String impUid) {
        return paymentJpaRepository.findByMerchantUid(impUid).orElseThrow();
    }
}
