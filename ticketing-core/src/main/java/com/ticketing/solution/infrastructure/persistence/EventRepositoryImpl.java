package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.event.EventRepository;
import com.ticketing.solution.domain.event.EventType;
import com.ticketing.solution.infrastructure.persistence.jpa.EventJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EventRepositoryImpl implements EventRepository {
    private final EventJpaRepository eventRepository;


    @Override
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow();
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void update(Long eventId, Event event) {
        Event original = eventRepository.findById(eventId).orElseThrow();
        original.update(event);
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> findEventsByEventType(EventType eventType, PageRequest pageRequest) {
        return eventRepository.findEventsByEventType(eventType, pageRequest);
    }
}
