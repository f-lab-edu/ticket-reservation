package com.ticketing.solution.api.reservation;

import com.ticketing.solution.api.pay.PaymentResponse;
import com.ticketing.solution.api.show.ShowResponse;
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
