package com.ticketing.solution.application.port.out.payment;

import com.ticketing.solution.domain.payment.Payment;

public interface ThirdPartyPaymentPort {

    Payment getPaymentInfo(String paymentId);

    void cancelPayment(String paymentId);
}
