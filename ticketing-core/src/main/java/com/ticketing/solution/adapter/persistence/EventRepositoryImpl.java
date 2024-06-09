package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.EventJpaRepository;
import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.adapter.persistence.jpa.mapper.EventMapper;
import com.ticketing.solution.application.port.out.EventRepository;
import com.ticketing.solution.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EventRepositoryImpl implements EventRepository {
    private final EventJpaRepository eventRepository;
    private final EventMapper eventMapper;


    @Override
    public Event findById(Long eventId) {
        return eventMapper.mapToDomain(eventRepository.findById(eventId).orElseThrow());
    }

    @Override
    public void save(Event event) {
        eventRepository.save(eventMapper.mapToEntity(event));
    }

    @Override
    public void update(Long eventId, Event event) {
        var original = eventRepository.findById(eventId).orElseThrow();
        original.update(eventMapper.mapToEntity(event));
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> findEventsByEventType(EventType eventType, PageRequest pageRequest) {
        return eventMapper.mapToDomains(eventRepository.findEventsByEventType(eventType, pageRequest));
    }
}
