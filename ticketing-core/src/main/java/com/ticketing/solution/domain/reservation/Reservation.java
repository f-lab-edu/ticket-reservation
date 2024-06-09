package com.ticketing.solution.domain.reservation;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.show.Show;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {
    private Long id;
    private ReservationStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Show show;
    private Seat seat;
    private Member member;
    private Payment payment;

}
