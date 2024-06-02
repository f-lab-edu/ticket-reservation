package com.ticketing.solution.application;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;

public interface ReservationFacade {

    void createReservation(Payment payment, Long showId, UserDetailsImpl userDetails);

    void cancelReservation(Long reservationId);
}
