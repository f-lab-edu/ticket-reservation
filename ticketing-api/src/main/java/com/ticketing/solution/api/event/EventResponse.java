package com.ticketing.solution.api.event;

import lombok.Builder;

@Builder
public record EventResponse(
        String name,
        String description,
        String date,
        String ticketingDate,
        String eventType,
        String createdDate,
        String modifiedDate
) {
}
