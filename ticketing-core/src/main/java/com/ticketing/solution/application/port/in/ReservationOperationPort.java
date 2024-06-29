package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;

import java.util.List;

public interface ReservationOperationPort {

    Reservation getReservationById(Long reservationId);

    Reservation getReservationByPayment(Payment payment);

    void createReservation(Payment payment, Long showId, Member member);

    List<Reservation> getReservations(int page, Member member);

    void cancelReservation(Long reservationId);

    void approveReservation(Reservation reservation);

}