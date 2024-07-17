package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.mapper.SeatJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.repository.SeatJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class SeatJpaAdapter implements SeatPersistencePort {
    private final SeatJpaRepository seatJpaRepository;
    private final SeatJpaMapper seatJpaMapper;

    @Override
    public Seat getSeat(Long seatId) {
        return seatJpaMapper.mapToDomain(seatJpaRepository.findById(seatId).orElseThrow());
    }

    @Override
    public Seat getSeatWithLock(Long seatId) {
        return seatJpaMapper.mapToDomain(seatJpaRepository.findByIdWithLock(seatId).orElseThrow());
    }

    @Override
    public List<Seat> getSeats(Long showId) {
        return seatJpaMapper.mapToDomains(seatJpaRepository.findByShowId(showId));
    }

    @Override
    public Seat addSeat(Seat seat) {
        return seatJpaMapper.mapToDomain(seatJpaRepository.save(seatJpaMapper.mapToEntity(seat)));
    }

    @Override
    @Transactional
    public void occupySeat(Seat seat, Member member) {
        seat.setCapacity(seat.getCapacity() - 1);
        seatJpaRepository.save(seatJpaMapper.mapToEntity(seat));
    }

    @Override
    @Transactional
    public void increaseSeatCapacity(Long seatId) {
        Seat seat = seatJpaMapper.mapToDomain(seatJpaRepository.findById(seatId).orElseThrow());
        seat.setCapacity(seat.getCapacity() + 1);
        seatJpaRepository.save(seatJpaMapper.mapToEntity(seat));
    }
}
