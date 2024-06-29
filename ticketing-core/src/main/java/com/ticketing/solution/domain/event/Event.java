package com.ticketing.solution.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Event {
    private Long id;
    private EventType eventType;
    private String eventName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate ticketingDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void update(Event event) {
        this.eventType = event.getEventType();
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.ticketingDate = event.getTicketingDate();
    }
}
