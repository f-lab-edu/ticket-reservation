package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.mapper.HallJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.repository.HallJpaRepository;
import com.ticketing.solution.application.port.out.persistence.HallPersistencePort;
import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HallJpaAdapter implements HallPersistencePort {
    private final HallJpaRepository hallJpaRepository;
    private final HallJpaMapper hallJpaMapper;

    @Override
    public Hall findById(Long hallId) {
        var hall = hallJpaRepository.findById(hallId).orElseThrow();
        return hallJpaMapper.mapToDomain(hall);
    }

    @Override
    public void save(Hall hall) {
        hallJpaRepository.save(hallJpaMapper.mapToEntity(hall));
    }

    @Override
    public void deleteById(Long hallId) {
        hallJpaRepository.deleteById(hallId);
    }

    @Override
    public void update(Hall hall) {
        hallJpaRepository.save(hallJpaMapper.mapToEntity(hall));
    }

}
