package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.application.port.in.SeatService;
import com.ticketing.solution.application.port.out.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

}
