package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.SeatClassJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatClassPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatClassJpaAdapter implements SeatClassPersistencePort {
    private final SeatClassJpaRepository seatClassJpaRepository;
}
