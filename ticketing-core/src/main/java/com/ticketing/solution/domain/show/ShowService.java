package com.ticketing.solution.domain.show;

import java.util.List;

public interface ShowService {
    Show getShow(Long showId);

    List<Show> getShows(int page);
    
    void deleteShow(Long showId);

    void addShow(Show show);
}
