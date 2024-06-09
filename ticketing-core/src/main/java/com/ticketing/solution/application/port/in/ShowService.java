package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.show.Show;
import java.util.List;

public interface ShowService {
    Show getShow(Long showId);

    List<Show> getShows(int page);
    
    void deleteShow(Long showId);

    void addShow(Show show);
}
