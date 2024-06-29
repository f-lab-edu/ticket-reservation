package com.ticketing.solution.domain.seat;

import com.ticketing.solution.domain.hall.Hall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Seat {
    private Long id;
    private int capacity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Hall hall;
    private SeatClass seatClass;
}
