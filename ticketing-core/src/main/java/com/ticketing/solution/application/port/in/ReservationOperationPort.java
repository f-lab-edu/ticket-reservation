package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.show.Show;

import java.util.List;

public interface ReservationOperationPort {

    Reservation getReservationById(Long reservationId);

    Reservation getReservationByPayment(Payment payment);

    void createReservation(Payment payment, Show show, Member member);

    List<Reservation> getReservations(int page, Member member);

    void cancelReservation(Long reservationId);

    void approveReservation(Reservation reservation);

}