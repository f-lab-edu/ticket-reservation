package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.seat.SeatClass;

public interface SeatClassPersistencePort {
    SeatClass getSeatClass(Long seatClassId);
}
