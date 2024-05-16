package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
}
