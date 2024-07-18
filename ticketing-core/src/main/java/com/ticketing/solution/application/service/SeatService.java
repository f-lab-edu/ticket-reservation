package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.SeatOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import com.ticketing.solution.application.service.exception.SeatAlreadyOccupiedException;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatService implements SeatOperationPort {

    private final SeatPersistencePort seatPersistencePort;

    @Override
    @Transactional
    public Seat getSeat(Long seatId) {
        return seatPersistencePort.getSeat(seatId);
    }

    @Override
    @Transactional
    public List<Seat> getSeats(Long showId) {
        return seatPersistencePort.getSeats(showId);
    }

    @Override
    @Transactional
    public void addSeat(Seat seat) {
        seatPersistencePort.addSeat(seat);
    }

    @Override
    @Transactional
    public void occupySeat(Long seatId, Member member) {
        int capacity = seatPersistencePort.getSeat(seatId).getCapacity();
        if (capacity == 0) {
            throw new SeatAlreadyOccupiedException();
        }
        seatPersistencePort.occupySeat(seatId, member);
    }
}
