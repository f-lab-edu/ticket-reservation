package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;

import java.util.List;

public interface SeatOperationPort {
    Seat getSeat(Long seatId);

    List<Seat> getSeats(Long showId);

    void addSeat(Seat seat);

    void occupySeat(Long seatId, Member member);

    void increaseSeatCapacity(Long seatId);

    Seat getSeatWithLock(Long seatId);

    void verifyPermission(Long seatId, Member member);
}
