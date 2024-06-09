package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.application.port.in.EventService;
import com.ticketing.solution.application.port.in.HallService;
import com.ticketing.solution.application.port.in.ShowService;
import com.ticketing.solution.application.port.out.ShowRepository;
import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final EventService eventService;
    private final HallService hallService;

    @Override
    @Transactional(readOnly = true)
    public Show getShow(Long showId) {
        return showRepository.findById(showId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShows(int page) {
        return showRepository.findAll(page);
    }

    @Override
    @Transactional
    public void deleteShow(Long showId) {
        showRepository.deleteById(showId);
    }

    @Override
    @Transactional
    public void addShow(Show show) {
        showRepository.save(show);
    }

    @Override
    @Transactional
    public void updateShow(Long showId, Show mapToShow, Long eventId, Long hallId) {
        Show show = showRepository.findById(showId);
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
        showRepository.save(show);
    }
}
