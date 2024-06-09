package com.ticketing.solution.application.port.out;

import com.ticketing.solution.domain.hall.Hall;

public interface HallRepository {

    Hall findById(Long hallId);

    void save(Hall hall);

    void deleteById(Long hallId);

    void update(Hall hall);
}
