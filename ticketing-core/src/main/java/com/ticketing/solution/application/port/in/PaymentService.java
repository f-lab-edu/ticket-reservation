package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.payment.Payment;

public interface PaymentService {
    void save(Payment paymentResult);

    Payment getPayment(Long paymentId);

    Payment getPaymentByMerchantUid(String merchantUid);

    void cancelPayment(Payment payment);
}
