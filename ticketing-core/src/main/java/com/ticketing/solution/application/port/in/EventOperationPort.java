package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.event.EventType;

import java.util.List;

public interface EventOperationPort {
    List<Event> getEvents(int page, EventType eventType);

    Event getEvent(Long eventId);

    void addEvent(Event event);

    void updateEvent(Long eventId, Event event);

    void deleteEvent(Long eventId);
}