package com.ticketing.solution.api.pay;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
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
                .build();
    }

    public ProcessPrePaymentCommand mapToProcessPrePaymentCommand(PaymentPreRequest paymentRequest) {
        return ProcessPrePaymentCommand.builder()
                .merchantUid(paymentRequest.merchantUid())
                .showId(paymentRequest.showId())
                .amount(paymentRequest.amount())
                .build();
    }
}
