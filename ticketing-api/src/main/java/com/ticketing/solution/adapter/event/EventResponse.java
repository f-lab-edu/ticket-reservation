package com.ticketing.solution.adapter.event;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EventResponse(
        String name,
        String description,
        String date,
        String ticketingDate,
        String eventType,
        LocalDate startDate,
        LocalDate endDate
) {
}
