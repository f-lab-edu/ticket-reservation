package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.SeatClassJpaRepository;
import com.ticketing.solution.application.port.out.SeatClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatClassRepositoryImpl implements SeatClassRepository {
    private final SeatClassJpaRepository seatClassJpaRepository;
}
