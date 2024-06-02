package com.ticketing.solution.api.pay;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentPreRequest(
        String merchant_uid,
        Long showId,
        BigDecimal amount
) {
}
