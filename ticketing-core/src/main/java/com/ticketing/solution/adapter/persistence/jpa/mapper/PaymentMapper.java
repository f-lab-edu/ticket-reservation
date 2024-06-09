package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final MemberMapper memberMapper;

    public Payment mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Payment payment) {
        return Payment.builder()
                .id(payment.getId())
                .impUid(payment.getImpUid())
                .merchantUid(payment.getMerchantUid())
                .approved(payment.isApproved())
                .member(memberMapper.mapToDomain(payment.getMember()))
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
                .member(memberMapper.mapToEntity(payment.getMember()))
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .dueDate(payment.getDueDate())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
