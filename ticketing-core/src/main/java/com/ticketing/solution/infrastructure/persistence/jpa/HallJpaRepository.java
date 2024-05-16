package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.hall.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallJpaRepository extends JpaRepository<Hall, Long> {
}
