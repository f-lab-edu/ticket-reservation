package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.hall.HallRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.HallJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HallRepositoryImpl implements HallRepository {
    private final HallJpaRepository hallRepository;

    @Override
    public Hall findById(Long hallId) {
        return hallRepository.findById(hallId).orElseThrow();
    }

    @Override
    public void save(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public void deleteById(Long hallId) {
        hallRepository.deleteById(hallId);
    }

}
