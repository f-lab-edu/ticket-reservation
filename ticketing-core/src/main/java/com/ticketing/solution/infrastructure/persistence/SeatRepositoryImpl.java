package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.seat.SeatRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository {
    private final SeatJpaRepository seatJpaRepository;
}
