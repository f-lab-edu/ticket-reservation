package com.ticketing.solution.domain.hall;

public interface HallRepository {

    Hall findById(Long hallId);

    void save(Hall hall);

    void deleteById(Long hallId);
}
