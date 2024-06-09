package com.ticketing.solution.infrastructure.thirdPartyPayment;

import com.ticketing.solution.domain.payment.Payment;

public interface ThirdPartyPaymentService {

    Payment getPaymentInfo(String paymentId);

    void cancelPayment(String paymentId);
}
