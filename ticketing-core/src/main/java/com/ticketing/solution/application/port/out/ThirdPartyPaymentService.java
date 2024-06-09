package com.ticketing.solution.application.port.out;

import com.ticketing.solution.domain.payment.Payment;

public interface ThirdPartyPaymentService {

    Payment getPaymentInfo(String paymentId);

    void cancelPayment(String paymentId);
}
