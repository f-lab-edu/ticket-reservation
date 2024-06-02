package com.ticketing.solution.domain.event;

import java.util.List;

public interface EventService {
    List<Event> getEvents(int page, EventType eventType);

    Event getEvent(Long eventId);

    void addEvent(Event event);

    void updateEvent(Long eventId, Event event);

    void deleteEvent(Long eventId);
}
