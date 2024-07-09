package com.ticketing.solution.adapter.waiting;

import lombok.Builder;

@Builder
public record WaitingResponse(
        boolean isAllowed,
        int rank
) {
}
