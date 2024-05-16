package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.seat.SeatClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatClassJpaRepository extends JpaRepository<SeatClass, Long> {
}
