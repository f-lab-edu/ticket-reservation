package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.seat.SeatClass;

public interface SeatClassOperationPort {
    SeatClass getSeatClass(Long seatClassId);
}
