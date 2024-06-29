package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.payment.Payment;

public interface PaymentPersistencePort {
    void save(Payment payment);

    Payment findById(Long id);

    Payment findByMerchantUid(String merchantUid);
}
