package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.hall.Hall;
public interface HallOperationPort {
    Hall getHall(Long hallId);

    void addHall(Hall mapToHall);

    void updateHall(Hall mapToHall);

    void deleteHall(Long hallId);
}
