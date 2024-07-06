package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.mapper.SeatClassJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.repository.SeatClassJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatClassPersistencePort;
import com.ticketing.solution.domain.seat.SeatClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatClassJpaAdapter implements SeatClassPersistencePort {
    private final SeatClassJpaRepository seatClassJpaRepository;
    private final SeatClassJpaMapper seatClassMapper;

    @Override
    public SeatClass getSeatClass(Long seatClassId) {
        return seatClassMapper.mapToDomain(seatClassJpaRepository.findById(seatClassId).orElseThrow());
    }
}
