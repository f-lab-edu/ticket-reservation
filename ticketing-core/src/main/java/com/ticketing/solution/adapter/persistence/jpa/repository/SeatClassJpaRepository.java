package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.SeatClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatClassJpaRepository extends JpaRepository<SeatClass, Long> {
}
