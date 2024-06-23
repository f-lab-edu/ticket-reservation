package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.out.persistence.ReservationPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationPersistencePort reservationPersistencePort;

    @Transactional(readOnly = true)
    public List<Reservation> getReservations(int page, Member member) {
        return reservationPersistencePort.findByMember(page, member);
    }

    @PostAuthorize("returnObject.member.email == authentication.name")
    @Transactional(readOnly = true)
    public Reservation getReservationById(Long reservationId) {
        return reservationPersistencePort.findById(reservationId);
    }

    @Transactional(readOnly = true)
    public Reservation getReservationByPayment(Payment payment) {
        return reservationPersistencePort.findByPayment(payment);
    }

    @Transactional
    public void approveReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.RESERVED);
        reservationPersistencePort.save(reservation);
    }

    @Transactional
    public void addReservation(Reservation reservation) {
        reservationPersistencePort.save(reservation);
    }

}
