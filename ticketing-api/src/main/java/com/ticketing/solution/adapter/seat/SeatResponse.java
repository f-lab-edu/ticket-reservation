package com.ticketing.solution.adapter.seat;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SeatResponse(
        String type,
        int capacity,
        BigDecimal price
) {
}
