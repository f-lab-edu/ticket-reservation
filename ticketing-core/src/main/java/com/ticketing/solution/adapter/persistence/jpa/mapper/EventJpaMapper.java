package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.event.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventJpaMapper {

    public Event mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Event event) {
        return Event.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .description(event.getDescription())
                .eventType(event.getEventType())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .ticketingDate(event.getTicketingDate())
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Event mapToEntity(Event event) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Event.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .description(event.getDescription())
                .eventType(event.getEventType())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .ticketingDate(event.getTicketingDate())
                .build();
    }

    public List<Event> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.Event> eventsByEventType) {
        return eventsByEventType.stream()
                .map(this::mapToDomain)
                .toList();
    }
}
