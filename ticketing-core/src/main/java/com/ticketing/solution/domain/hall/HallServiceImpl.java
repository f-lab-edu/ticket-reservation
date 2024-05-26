package com.ticketing.solution.domain.hall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService{
    private final HallRepository hallRepository;

    @Override
    public Hall getHall(Long hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    public void addHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public void updateHall(Long hallId, Hall hall) {
        Hall findHall = hallRepository.findById(hallId);
        findHall.update(hall);
    }

    @Override
    public void deleteHall(Long hallId) {
        hallRepository.deleteById(hallId);
    }

}
