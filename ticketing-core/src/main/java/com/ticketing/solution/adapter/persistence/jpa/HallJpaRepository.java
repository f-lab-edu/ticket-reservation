package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallJpaRepository extends JpaRepository<Hall, Long> {
}
