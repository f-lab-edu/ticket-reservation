package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.EventJpaRepository;
import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.adapter.persistence.jpa.mapper.EventJpaMapper;
import com.ticketing.solution.application.port.out.persistence.EventPersistencePort;
import com.ticketing.solution.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EventJpaAdapter implements EventPersistencePort {
    private final EventJpaRepository eventJpaRepository;
    private final EventJpaMapper eventJpaMapper;


    @Override
    public Event findById(Long eventId) {
        return eventJpaMapper.mapToDomain(eventJpaRepository.findById(eventId).orElseThrow());
    }

    @Override
    public void save(Event event) {
        eventJpaRepository.save(eventJpaMapper.mapToEntity(event));
    }

    @Override
    public void update(Long eventId, Event event) {
        var original = eventJpaRepository.findById(eventId).orElseThrow();
        original.update(eventJpaMapper.mapToEntity(event));
    }

    @Override
    public void delete(Long eventId) {
        eventJpaRepository.deleteById(eventId);
    }

    @Override
    public List<Event> findEventsByEventType(EventType eventType, PageRequest pageRequest) {
        return eventJpaMapper.mapToDomains(eventJpaRepository.findEventsByEventType(eventType, pageRequest));
    }
}
