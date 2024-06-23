package com.ticketing.solution.application.port.out.persistence;


import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;

import java.util.List;

public interface ReservationPersistencePort {
    Reservation save(Reservation reservation);

    Reservation findById(Long reservationId);

    List<Reservation> findByMember(int page, Member member);

    Reservation findByPayment(Payment payment);
}
