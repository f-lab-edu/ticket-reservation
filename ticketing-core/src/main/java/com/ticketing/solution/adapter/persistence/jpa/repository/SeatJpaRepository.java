package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowId(Long showId);
}
