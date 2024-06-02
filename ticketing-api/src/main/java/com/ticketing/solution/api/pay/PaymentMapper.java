package com.ticketing.solution.api.pay;

import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    public PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .approved(payment.isApproved())
                .dueDate(payment.getDueDate())
                .paymentDate(payment.getPaymentDate())
                .cancelDate(payment.getCancelDate())
                .createdDate(payment.getCreatedDate())
                .modifiedDate(payment.getModifiedDate())
                .build();
    }

}
