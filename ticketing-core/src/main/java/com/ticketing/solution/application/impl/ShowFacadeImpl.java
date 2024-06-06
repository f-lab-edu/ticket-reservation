package com.ticketing.solution.application.impl;

import com.ticketing.solution.application.ShowFacade;
import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.event.EventService;
import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.hall.HallService;
import com.ticketing.solution.domain.show.Show;
import com.ticketing.solution.domain.show.ShowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShowFacadeImpl implements ShowFacade {

    private final ShowService showService;
    private final EventService eventService;
    private final HallService hallService;

    @Override
    @Transactional
    public void updateShow(Long showId, Show mapToShow, Long eventId, Long hallId) {
        Show show = showService.getShow(showId);
        Event event = eventService.getEvent(eventId);
        Hall hall = hallService.getHall(hallId);
        show.update(mapToShow, event, hall);
    }

    @Override
    @Transactional
    public void addShow(Show show, Long eventId, Long hallId) {
        Event event = eventService.getEvent(eventId);
        Hall hall = hallService.getHall(hallId);
        show.update(show, event, hall);
        showService.addShow(show);
    }
}
