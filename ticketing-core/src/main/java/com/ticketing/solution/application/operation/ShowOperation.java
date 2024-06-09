package com.ticketing.solution.application.operation;

import com.ticketing.solution.application.port.in.EventOperationPort;
import com.ticketing.solution.application.port.in.HallOperationPort;
import com.ticketing.solution.application.port.in.ShowOperationPort;
import com.ticketing.solution.application.port.out.persistence.ShowPersistencePort;
import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowOperation implements ShowOperationPort {

    private final ShowPersistencePort showPersistencePort;
    private final ShowOperationPort showOperationPort;
    private final EventOperationPort eventOperationPort;
    private final HallOperationPort hallOperationPort;

    @Override
    @Transactional(readOnly = true)
    public Show getShow(Long showId) {
        return showPersistencePort.findById(showId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShows(int page) {
        return showPersistencePort.findAll(page);
    }

    @Override
    @Transactional
    public void deleteShow(Long showId) {
        showPersistencePort.deleteById(showId);
    }

    @Override
    @Transactional
    public void updateShow(Long showId, Show mapToShow, Long eventId, Long hallId) {
        Show show = showOperationPort.getShow(showId);
        Event event = eventOperationPort.getEvent(eventId);
        Hall hall = hallOperationPort.getHall(hallId);
        show.update(mapToShow, event, hall);
        showPersistencePort.save(show);
    }

    @Override
    @Transactional
    public void addShow(Show show, Long eventId, Long hallId) {
        Event event = eventOperationPort.getEvent(eventId);
        Hall hall = hallOperationPort.getHall(hallId);
        show.update(show, event, hall);
        showPersistencePort.save(show);
    }
}
