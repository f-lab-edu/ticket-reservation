package com.ticketing.solution.api.pay;

import lombok.Builder;

@Builder
public record PaymentPostRequest(
        String imp_uid,
        String status,
        String cancellation_id,
        String name
) {
}
