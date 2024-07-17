package com.ticketing.solution.domain.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProcessPrePaymentCommand(
        String merchantUid,
        Long seatId,
        BigDecimal amount
) {
}
