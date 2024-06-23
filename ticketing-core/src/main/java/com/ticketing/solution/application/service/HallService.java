package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.HallOperationPort;
import com.ticketing.solution.application.port.out.persistence.HallPersistencePort;
import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HallService implements HallOperationPort {
    private final HallPersistencePort hallPersistencePort;

    @Override
    @Transactional(readOnly = true)
    public Hall getHall(Long hallId) {
        return hallPersistencePort.findById(hallId);
    }

    @Override
    @Transactional
    public void addHall(Hall hall) {
        hallPersistencePort.save(hall);
    }

    @Override
    @Transactional
    public void updateHall(Long hallId, Hall hallRequest) {
        Hall hall = hallPersistencePort.findById(hallId);
        hall.update(hallRequest);
        hallPersistencePort.update(hall);
    }

    @Override
    @Transactional
    public void deleteHall(Long hallId) {
        hallPersistencePort.deleteById(hallId);
    }

}
