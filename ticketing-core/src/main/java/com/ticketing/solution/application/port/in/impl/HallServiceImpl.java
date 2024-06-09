package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.application.port.in.HallService;
import com.ticketing.solution.application.port.out.HallRepository;
import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    @Override
    @Transactional(readOnly = true)
    public Hall getHall(Long hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    @Transactional
    public void addHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    @Transactional
    public void updateHall(Hall hall) {
        hallRepository.update(hall);
    }

    @Override
    @Transactional
    public void deleteHall(Long hallId) {
        hallRepository.deleteById(hallId);
    }

}
