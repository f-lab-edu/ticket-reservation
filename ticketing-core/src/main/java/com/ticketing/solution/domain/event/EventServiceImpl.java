package com.ticketing.solution.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

}
