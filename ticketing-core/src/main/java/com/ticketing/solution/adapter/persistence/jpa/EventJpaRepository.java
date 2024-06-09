package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.entity.Event;
import com.ticketing.solution.domain.event.EventType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByEventType(EventType eventType, PageRequest pageRequest);
}
