package com.ticketing.solution.infrastructure.thirdPartyPayment.portOne;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.PaymentMethod;

public class PortOnePaymentMapper {

    public Payment mapToPayment(com.siot.IamportRestClient.response.Payment portOnePayment) {
        return Payment.builder()
                .impUid(portOnePayment.getImpUid())
                .merchantUid(portOnePayment.getMerchantUid())
                .amount(portOnePayment.getAmount())
                .paymentDate(portOnePayment.getPaidAt())
                .method(PaymentMethod.valueOf(portOnePayment.getPayMethod()))
                .build();
    }
}
