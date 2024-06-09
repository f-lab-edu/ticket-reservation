package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.HallJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.HallMapper;
import com.ticketing.solution.application.port.out.HallRepository;
import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HallRepositoryImpl implements HallRepository {
    private final HallJpaRepository hallRepository;
    private final HallMapper hallMapper;

    @Override
    public Hall findById(Long hallId) {
        var hall = hallRepository.findById(hallId).orElseThrow();
        return hallMapper.mapToDomain(hall);
    }

    @Override
    public void save(Hall hall) {
        hallRepository.save(hallMapper.mapToEntity(hall));
    }

    @Override
    public void deleteById(Long hallId) {
        hallRepository.deleteById(hallId);
    }

    @Override
    public void update(Hall hall) {
        hallRepository.save(hallMapper.mapToEntity(hall));
    }

}
