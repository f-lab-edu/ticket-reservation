package com.ticketing.solution.adapter.seat;

import lombok.Builder;

@Builder
public record SeatRequest(
        String name,
        int number,
        int capacity,
        Long SeatClassId,
        Long HallId,
        Long ShowId
) {
}
