package com.ticketing.solution.application.operation;

import com.ticketing.solution.application.port.in.SeatOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatOperation implements SeatOperationPort {
    private final SeatPersistencePort seatPersistencePort;

}
