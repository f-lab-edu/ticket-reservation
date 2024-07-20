package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.seat.SeatOccupy;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatOccupyPersistencePort {

    void addSeatOccupy(SeatOccupy seatOccupy);

    SeatOccupy getSeatOccupy(Long seatOccupyId);

    List<SeatOccupy> releaseSeatOccupancy(LocalDateTime minus);

    SeatOccupy findBySeat(Seat seat);

    void setReserved(boolean isReserved, SeatOccupy seatOccupy);

    SeatOccupy getSeatOccupyBySeatId(Long seatId);
}
