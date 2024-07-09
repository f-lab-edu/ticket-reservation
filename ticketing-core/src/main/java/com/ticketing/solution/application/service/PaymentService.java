package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.out.persistence.PaymentPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentPersistencePort paymentPersistencePort;

    @Transactional
    public void save(Payment paymentResult) {
        paymentPersistencePort.save(paymentResult);
    }

    @Transactional(readOnly = true)
    public Payment getPaymentByIdAndMember(Long paymentId, Member member) {
        return paymentPersistencePort.findByIdAndMember(paymentId, member);
    }

    @Transactional(readOnly = true)
    public Payment getPaymentByMerchantUid(String merchantUid) {
        return paymentPersistencePort.findByMerchantUid(merchantUid);
    }

    @Transactional
    public void cancelPayment(Payment payment) {
        payment.setCancelDate(Date.from(Instant.from(LocalDate.now())));
        paymentPersistencePort.save(payment);
    }
}
