package com.ticketing.solution.domain.show;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.hall.Hall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Show {
    private Long id;
    private LocalDate eventDate;
    private int maxOccupancy;
    private int playTime;
    private String location;
    private Hall hall;
    private Event event;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void update(Show mapToShow, Event event, Hall hall) {
        this.eventDate = mapToShow.getEventDate();
        this.maxOccupancy = mapToShow.getMaxOccupancy();
        this.playTime = mapToShow.getPlayTime();
        this.location = mapToShow.getLocation();
        this.event = event;
        this.hall = hall;
    }
}
