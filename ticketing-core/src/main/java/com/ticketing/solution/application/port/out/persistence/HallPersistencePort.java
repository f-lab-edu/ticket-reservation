package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.hall.Hall;

public interface HallPersistencePort {

    Hall findById(Long hallId);

    void save(Hall hall);

    void deleteById(Long hallId);

    void update(Hall hall);
}
