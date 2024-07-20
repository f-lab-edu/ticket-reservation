package com.ticketing.solution.domain.seat;

import com.ticketing.solution.domain.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SeatOccupy {
    private Long id;
    private Seat seat;
    private Member member;
    private boolean isReserved;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
