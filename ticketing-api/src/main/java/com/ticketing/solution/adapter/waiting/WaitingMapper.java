package com.ticketing.solution.adapter.waiting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WaitingMapper {
    public WaitingResponse mapToResponse(int rank) {
        return WaitingResponse.builder()
                .rank(rank)
                .isAllowed(rank > 0)
                .build();
    }

    public WaitingResponse mapToResponse(boolean isAllowed) {
        return WaitingResponse.builder()
                .isAllowed(isAllowed)
                .build();
    }
}
