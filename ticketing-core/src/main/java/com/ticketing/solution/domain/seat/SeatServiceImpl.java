package com.ticketing.solution.domain.seat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService{
    private final SeatRepository seatRepository;

}
