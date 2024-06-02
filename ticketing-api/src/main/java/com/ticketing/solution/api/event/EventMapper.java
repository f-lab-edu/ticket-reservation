package com.ticketing.solution.api.event;

import com.ticketing.solution.domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventMapper {
    public Event mapToEvent(EventRequest eventRequest) {
        return Event.builder()
                .eventType(eventRequest.eventType())
                .eventName(eventRequest.eventName())
                .description(eventRequest.description())
                .startDate(getDateAsLocalDate(eventRequest.startDate()))
                .endDate(getDateAsLocalDate(eventRequest.endDate()))
                .ticketingDate(getDateAsLocalDate(eventRequest.ticketingDate()))
                .build();
    }

    public EventResponse mapToEventResponse(Event event) {
       return EventResponse.builder()
                .name(event.getEventName())
                .description(event.getDescription())
                .date(event.getStartDate().toString())
                .ticketingDate(event.getTicketingDate().toString())
                .eventType(event.getEventType().name())
                .createdDate(event.getCreatedDate().toString())
                .modifiedDate(event.getModifiedDate().toString())
                .build();
    }

    public List<EventResponse> mapToEventList(List<Event> events) {
        return events.stream()
                .map(this::mapToEventResponse)
                .toList();
    }

    public LocalDate getDateAsLocalDate(String date) {
        return LocalDate.parse(date);
    }
}
