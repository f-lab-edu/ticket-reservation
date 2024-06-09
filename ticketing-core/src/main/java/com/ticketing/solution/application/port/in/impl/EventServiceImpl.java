package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.application.port.in.EventService;
import com.ticketing.solution.application.port.out.EventRepository;
import com.ticketing.solution.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(int page, EventType eventType) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "id"));
        return eventRepository.findEventsByEventType(eventType, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void updateEvent(Long eventId, Event event) {
        eventRepository.update(eventId, event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        eventRepository.delete(eventId);
    }
}
