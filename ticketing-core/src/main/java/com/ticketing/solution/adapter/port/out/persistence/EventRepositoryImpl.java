package com.ticketing.solution.adapter.port.out.persistence;

import com.ticketing.solution.domain.event.EventRepository;
import com.ticketing.solution.adapter.port.out.persistence.jpa.EventJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EventRepositoryImpl implements EventRepository {
    private final EventJpaRepository eventRepository;
}
