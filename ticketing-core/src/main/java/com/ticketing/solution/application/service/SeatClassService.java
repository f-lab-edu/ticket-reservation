package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.SeatClassOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatClassPersistencePort;
import com.ticketing.solution.domain.seat.SeatClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatClassService implements SeatClassOperationPort {
    private final SeatClassPersistencePort seatClassPersistencePort;

    @Override
    public SeatClass getSeatClass(Long seatClassId) {
        return seatClassPersistencePort.getSeatClass(seatClassId);
    }
}
