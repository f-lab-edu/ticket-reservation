package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.reservation.ReservationRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;
}
