package com.ticketing.solution.api.show;

import java.time.LocalDate;

public record ShowRequest (
        LocalDate eventDate,
        int maxOccupancy,
        int playTime,
        String location,
        Long eventId,
        Long hallId
){
}
