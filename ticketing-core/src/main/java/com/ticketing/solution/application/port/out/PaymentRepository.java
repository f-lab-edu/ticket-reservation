package com.ticketing.solution.application.port.out;

import com.ticketing.solution.domain.payment.Payment;

public interface PaymentRepository {
    void save(Payment payment);

    Payment findById(Long id);

    Payment findByMerchantUid(String merchantUid);
}
