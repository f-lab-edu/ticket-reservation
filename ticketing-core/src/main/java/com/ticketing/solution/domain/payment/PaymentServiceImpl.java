package com.ticketing.solution.domain.payment;

import com.ticketing.solution.infrastructure.portOne.PortOneClient;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final PortOneClient portOneClient;

    @Override
    @Transactional
    public void save(Payment paymentResult) {
        paymentRepository.save(paymentResult);
    }

    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("returnObject.member.email == authentication.name")
    public Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByMerchantUid(String merchantUid) {
        return paymentRepository.findByMerchantUid(merchantUid);
    }

    @Override
    @PreAuthorize("#payment.member.email == authentication.name")
    @Transactional
    public void cancelPayment(Payment payment) {
        portOneClient.cancelPayment(payment.getImpUid());
        payment.setCancelDate(Date.from(Instant.from(LocalDate.now())));
    }

}
