package com.ticketing.solution.domain.hall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService{
    private final HallRepository hallRepository;

}
