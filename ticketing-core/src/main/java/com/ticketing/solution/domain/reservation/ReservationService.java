package com.ticketing.solution.domain.reservation;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;

import java.util.List;

public interface ReservationService {

    void addReservation(Reservation reservation);

    List<Reservation> getReservations(int page, UserDetailsImpl userDetails);

    Reservation getReservation(Long reservationId);

    Reservation getReservationByPayment(Payment payment);

    void approveReservation(Reservation reservation);
}