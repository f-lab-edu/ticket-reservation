package com.ticketing.solution.adapter.pay;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentWebMapper {

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
                .seatId(paymentRequest.seatId())
                .amount(paymentRequest.amount())
                .build();
    }
}
