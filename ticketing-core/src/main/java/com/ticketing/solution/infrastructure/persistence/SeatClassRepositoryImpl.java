package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.seat.SeatClassRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.SeatClassJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatClassRepositoryImpl implements SeatClassRepository {
    private final SeatClassJpaRepository seatClassJpaRepository;
}
