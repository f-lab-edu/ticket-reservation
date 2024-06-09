package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.SeatJpaRepository;
import com.ticketing.solution.application.port.out.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository {
    private final SeatJpaRepository seatJpaRepository;
}
