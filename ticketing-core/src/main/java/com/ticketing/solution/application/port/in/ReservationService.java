package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;

import java.util.List;

public interface ReservationService {

    void addReservation(Reservation reservation);

    List<Reservation> getReservations(int page, Member member);

    Reservation getReservation(Long reservationId);

    Reservation getReservationByPayment(Payment payment);

    void approveReservation(Reservation reservation);
}