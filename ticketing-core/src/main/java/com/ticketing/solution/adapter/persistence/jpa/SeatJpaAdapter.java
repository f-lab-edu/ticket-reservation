package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.SeatJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatJpaAdapter implements SeatPersistencePort {
    private final SeatJpaRepository seatJpaRepository;
}
