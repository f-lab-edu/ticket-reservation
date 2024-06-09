package com.ticketing.solution.application.port.out;


import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation save(Reservation reservation);

    Reservation findById(Long reservationId);

    List<Reservation> findByMember(int page, Member member);

    void deleteById(Long reservationId);

    Reservation findByPayment(Payment payment);
}
