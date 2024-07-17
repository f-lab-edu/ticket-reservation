package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.Seat;
import com.ticketing.solution.adapter.persistence.jpa.entity.SeatOccupy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeatOccupyJpaRepository extends JpaRepository<SeatOccupy, Long> {

    @Modifying
    @Query(value = "DELETE FROM seat_occupy WHERE is_reserved = true AND created_date < :sevenMinutesAgo", nativeQuery = true)
    List<SeatOccupy> deleteByIsReservedTrueAndCreatedDateBefore(LocalDateTime sevenMinutesAgo);

    SeatOccupy findBySeat(Seat seat);

    Optional<SeatOccupy> findBySeatId(Long seatId);
}