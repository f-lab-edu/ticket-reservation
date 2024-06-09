package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.PaymentJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.PaymentMapper;
import com.ticketing.solution.application.port.out.PaymentRepository;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(paymentMapper.mapToEntity(payment));
    }

    @Override
    public Payment findById(Long id) {
        return paymentMapper.mapToDomain(paymentJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Payment findByMerchantUid(String impUid) {
        return paymentMapper.mapToDomain(paymentJpaRepository.findByMerchantUid(impUid).orElseThrow());
    }
}
