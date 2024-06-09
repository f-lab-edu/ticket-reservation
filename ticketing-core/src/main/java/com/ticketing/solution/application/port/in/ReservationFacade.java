package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;

public interface ReservationFacade {

    void createReservation(Payment payment, Long showId, Member member);

    void cancelReservation(Long reservationId);

    Reservation getReservationByPayment(Payment payment);

    void approveReservation(Reservation reservation);
}
