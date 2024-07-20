package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.mapper.SeatJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.mapper.SeatOccupyJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.repository.SeatOccupyJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatOccupyPersistencePort;
import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.seat.SeatOccupy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class SeatOccupyJpaAdapter implements SeatOccupyPersistencePort {

    private final SeatOccupyJpaRepository seatOccupyJpaRepository;

    private final SeatOccupyJpaMapper seatOccupyJpaMapper;

    private final SeatJpaMapper seatJpaMapper;

    public void addSeatOccupy(SeatOccupy seatOccupy) {
        seatOccupyJpaRepository.save(seatOccupyJpaMapper.mapToEntity(seatOccupy));
    }

    public SeatOccupy getSeatOccupy(Long seatOccupyId) {
        return seatOccupyJpaMapper.mapToDomain(seatOccupyJpaRepository.findById(seatOccupyId).orElseThrow());
    }

    @Override
    public List<SeatOccupy> releaseSeatOccupancy(LocalDateTime sevenMinutesAgo) {
        return seatOccupyJpaMapper.mapToDomains(seatOccupyJpaRepository.deleteByIsReservedTrueAndCreatedDateBefore(sevenMinutesAgo));
    }

    @Override
    public SeatOccupy findBySeat(Seat seat) {
        return seatOccupyJpaMapper.mapToDomain(seatOccupyJpaRepository.findBySeat(seatJpaMapper.mapToEntity(seat)));
    }

    @Override
    public void setReserved(boolean isReserved, SeatOccupy seatOccupy) {
        seatOccupy.setReserved(isReserved);
        seatOccupyJpaRepository.save(seatOccupyJpaMapper.mapToEntity(seatOccupy));
    }

    @Override
    public SeatOccupy getSeatOccupyBySeatId(Long seatId) {
        return seatOccupyJpaMapper.mapToDomain(seatOccupyJpaRepository.findBySeatId(seatId).orElseThrow());
    }
}
