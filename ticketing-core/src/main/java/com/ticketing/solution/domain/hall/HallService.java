package com.ticketing.solution.domain.hall;

public interface HallService {
    Hall getHall(Long hallId);

    void addHall(Hall mapToHall);

    void updateHall(Long hallId, Hall mapToHall);

    void deleteHall(Long hallId);
}
