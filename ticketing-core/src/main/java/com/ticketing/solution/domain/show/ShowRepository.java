package com.ticketing.solution.domain.show;

import java.util.List;

public interface ShowRepository {
    Show findById(Long showId);

    void save(Show mapToShow);

    void deleteById(Long showId);

    List<Show> findAll(int page);
}
