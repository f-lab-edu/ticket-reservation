package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentJpaMapper {

    private final MemberJpaMapper memberJpaMapper;

    public Payment mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Payment payment) {
        return Payment.builder()
                .id(payment.getId())
                .impUid(payment.getImpUid())
                .merchantUid(payment.getMerchantUid())
                .approved(payment.isApproved())
                .member(memberJpaMapper.mapToDomain(payment.getMember()))
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .dueDate(payment.getDueDate())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Payment mapToEntity(Payment payment) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Payment.builder()
                .id(payment.getId())
                .impUid(payment.getImpUid())
                .merchantUid(payment.getMerchantUid())
                .approved(payment.isApproved())
                .member(memberJpaMapper.mapToEntity(payment.getMember()))
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .dueDate(payment.getDueDate())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
