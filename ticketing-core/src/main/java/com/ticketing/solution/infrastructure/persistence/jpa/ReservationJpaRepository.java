package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
