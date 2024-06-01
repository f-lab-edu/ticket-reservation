package com.ticketing.solution.adapter.port.out.persistence.jpa;

import com.ticketing.solution.domain.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
}
