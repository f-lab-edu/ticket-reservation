package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.entity.SeatClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatClassJpaRepository extends JpaRepository<SeatClass, Long> {
}
