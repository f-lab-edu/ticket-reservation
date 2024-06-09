package com.ticketing.solution.domain.payment;

public interface PaymentRepository {
    void save(Payment payment);

    Payment findById(Long id);

    Payment findByMerchantUid(String merchantUid);
}
