package com.ticketing.solution.application;

import com.ticketing.solution.domain.show.Show;

public interface ShowFacade {
    void updateShow(Long showId, Show mapToShow, Long eventId, Long hallId);

    void addShow(Show mapToShow, Long eventId, Long hallId);
}
