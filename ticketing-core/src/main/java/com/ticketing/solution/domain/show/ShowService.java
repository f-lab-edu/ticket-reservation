package com.ticketing.solution.domain.show;

import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;

import java.util.List;

public interface ShowService {
    Show getShow(Long showId);

    List<Show> getShows(UserDetailsImpl userDetails);
    
    void deleteShow(Long showId);

    void addShow(Show show);
}
