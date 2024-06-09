package com.ticketing.solution.adapter.reservation;

import com.ticketing.solution.adapter.pay.PaymentResponse;
import com.ticketing.solution.adapter.show.ShowResponse;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ReservationResponse (
        Long id,
        String status,
        LocalDateTime reservationDate,
        ShowResponse show,
        PaymentResponse payment
) { }
