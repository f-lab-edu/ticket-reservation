package com.ticketing.solution.api.hall;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HallResponse(
        String name,
        String address,
        int number,
        int capacity,
        String seatImage,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {}
