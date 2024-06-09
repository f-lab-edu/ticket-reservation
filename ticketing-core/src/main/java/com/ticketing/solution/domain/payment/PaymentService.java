package com.ticketing.solution.domain.payment;

public interface PaymentService {
    void save(Payment paymentResult);

    Payment getPayment(Long paymentId);

    Payment getPaymentByMerchantUid(String merchantUid);

    void cancelPayment(Payment payment);
}
