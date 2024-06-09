package com.ticketing.solution.api.pay;

import com.ticketing.solution.domain.payment.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record PaymentResponse(
        String id,
        BigDecimal amount,
        PaymentMethod method,
        boolean approved,
        Date dueDate,
        Date paymentDate,
        Date cancelDate
) {
}
