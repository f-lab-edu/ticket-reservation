package com.ticketing.solution.adapter.show;

import com.ticketing.solution.adapter.event.EventResponse;
import com.ticketing.solution.adapter.hall.HallResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ShowResponse (
        LocalDate eventDate,
        int maxOccupancy,
        int playTime,
        String location,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        HallResponse hall,
        EventResponse event
){
}
