package com.ticketing.solution.domain.seat;

import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.show.Show;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Seat {
    private Long id;
    private int capacity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Hall hall;
    private SeatClass seatClass;
    private Show show;
}
