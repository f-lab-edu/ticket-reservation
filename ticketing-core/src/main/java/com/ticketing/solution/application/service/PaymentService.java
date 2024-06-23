package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.out.persistence.PaymentPersistencePort;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostAuthorize("returnObject.member.email == authentication.name")
    public Payment getPaymentById(Long paymentId) {
        return paymentPersistencePort.findById(paymentId);
    }

    @Transactional(readOnly = true)
    public Payment getPaymentByMerchantUid(String merchantUid) {
        return paymentPersistencePort.findByMerchantUid(merchantUid);
    }

    @PreAuthorize("#payment.member.email == authentication.name")
    @Transactional
    public void cancelPayment(Payment payment) {
        payment.setCancelDate(Date.from(Instant.from(LocalDate.now())));
        paymentPersistencePort.save(payment);
    }
}
