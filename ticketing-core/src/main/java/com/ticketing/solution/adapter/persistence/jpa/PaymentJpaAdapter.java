package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.PaymentJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.PaymentJpaMapper;
import com.ticketing.solution.application.port.out.persistence.PaymentPersistencePort;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentJpaAdapter implements PaymentPersistencePort {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentJpaMapper paymentJpaMapper;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(paymentJpaMapper.mapToEntity(payment));
    }

    @Override
    public Payment findById(Long id) {
        return paymentJpaMapper.mapToDomain(paymentJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Payment findByMerchantUid(String impUid) {
        return paymentJpaMapper.mapToDomain(paymentJpaRepository.findByMerchantUid(impUid).orElseThrow());
    }
}