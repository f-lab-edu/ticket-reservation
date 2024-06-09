package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.show.Show;
import java.util.List;

public interface ShowPersistencePort {
    Show findById(Long showId);

    void save(Show mapToShow);

    void deleteById(Long showId);

    List<Show> findAll(int page);
}
