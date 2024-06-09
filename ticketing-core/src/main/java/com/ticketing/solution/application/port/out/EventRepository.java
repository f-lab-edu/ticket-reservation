package com.ticketing.solution.application.port.out;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.event.EventType;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EventRepository {
    Event findById(Long eventId);

    void save(Event event);

    void update(Long eventId, Event event);

    void delete(Long eventId);

    List<Event> findEventsByEventType(EventType eventType, PageRequest pageRequest);
}
