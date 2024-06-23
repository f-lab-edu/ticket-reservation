package com.ticketing.solution.application.service;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.application.port.in.EventOperationPort;
import com.ticketing.solution.application.port.out.persistence.EventPersistencePort;
import com.ticketing.solution.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService implements EventOperationPort {
    private final EventPersistencePort eventPersistencePort;

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(int page, EventType eventType) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "id"));
        return eventPersistencePort.findEventsByEventType(eventType, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(Long eventId) {
        return eventPersistencePort.findById(eventId);
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        eventPersistencePort.save(event);
    }

    @Override
    @Transactional
    public void updateEvent(Long eventId, Event event) {
        eventPersistencePort.update(eventId, event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        eventPersistencePort.delete(eventId);
    }
}
