package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.SeatOccupyOperationPort;
import com.ticketing.solution.application.port.in.SeatOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import com.ticketing.solution.application.service.exception.SeatAlreadyOccupiedException;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.seat.SeatOccupy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatService implements SeatOperationPort {

    private final SeatPersistencePort seatPersistencePort;

    private final SeatOccupyOperationPort seatOccupyOperationPort;

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
        Seat seat = getSeatWithLock(seatId);
        seatPersistencePort.occupySeat(seat, member);
        seatOccupyOperationPort.addSeatOccupy(SeatOccupy.builder()
                .isReserved(false)
                .seat(seat)
                .member(member)
                .build());
    }

    @Override
    @Transactional
    public Seat getSeatWithLock(Long seatId) {
        Seat seat = seatPersistencePort.getSeatWithLock(seatId);
        if (seat.getCapacity() <= 0) {
            throw new SeatAlreadyOccupiedException();
        }
        return seat;
    }

    @Override
    public void verifyPermission(Long seatId, Member member) {
        seatOccupyOperationPort.verifyPermission(seatId, member);
    }

    @Override
    @Transactional
    public void increaseSeatCapacity(Long seatId) {
        seatPersistencePort.increaseSeatCapacity(seatId);
    }
}
