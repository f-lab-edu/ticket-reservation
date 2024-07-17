package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.seat.SeatOccupy;

public interface SeatOccupyOperationPort {
    void addSeatOccupy(SeatOccupy build);

    SeatOccupy findBySeat(Seat seat);

    void setReserved(boolean b, SeatOccupy seatOccupy);

    void verifyPermission(Long seatId, Member member);
}
