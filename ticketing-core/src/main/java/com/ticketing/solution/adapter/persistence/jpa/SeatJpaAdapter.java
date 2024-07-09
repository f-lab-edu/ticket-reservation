package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.mapper.SeatJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.repository.SeatJpaRepository;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Seat> getSeats(Long showId) {
        return seatJpaMapper.mapToDomains(seatJpaRepository.findByShowId(showId));
    }

    @Override
    public void addSeat(Seat seat) {
        seatJpaRepository.save(seatJpaMapper.mapToEntity(seat));
    }

    @Override
    public void occupySeat(Long seatId, Member member) {
        Seat seat = seatJpaMapper.mapToDomain(seatJpaRepository.findById(seatId).orElseThrow());
        seat.setCapacity(0);
        seatJpaRepository.save(seatJpaMapper.mapToEntity(seat));
    }
}
