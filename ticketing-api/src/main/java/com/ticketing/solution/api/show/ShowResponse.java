package com.ticketing.solution.api.show;

import com.ticketing.solution.api.event.EventResponse;
import com.ticketing.solution.api.hall.HallResponse;
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
