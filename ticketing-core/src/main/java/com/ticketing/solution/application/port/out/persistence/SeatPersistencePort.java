package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;

import java.util.List;

public interface SeatPersistencePort {
    Seat getSeat(Long seatId);

    Seat getSeatWithLock(Long seatId);
    List<Seat> getSeats(Long showId);

    Seat addSeat(Seat seat);

    void occupySeat(Seat seat, Member member);

    void increaseSeatCapacity(Long seatId);
}
