package com.ticketing.solution.adapter.port.out.persistence.jpa;

import com.ticketing.solution.domain.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowJpaRepository extends JpaRepository<Show, Long> {
}
