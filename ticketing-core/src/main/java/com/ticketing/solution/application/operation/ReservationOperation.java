package com.ticketing.solution.application.operation;

import com.ticketing.solution.application.port.in.PaymentOperationPort;
import com.ticketing.solution.application.port.in.ReservationOperationPort;
import com.ticketing.solution.application.port.in.ShowOperationPort;
import com.ticketing.solution.application.port.out.persistence.ReservationPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationStatus;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationOperation implements ReservationOperationPort {
    private final ReservationPersistencePort reservationPersistencePort;

    private final ReservationOperationPort reservationOperationPort;

    private final PaymentOperationPort paymentOperationPort;

    private final ShowOperationPort showOperationPort;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservations(int page, Member member) {
        return reservationPersistencePort.findByMember(page, member);
    }

    @Override
    @PostAuthorize("returnObject.member.email == authentication.name")
    @Transactional(readOnly = true)
    public Reservation getReservationById(Long reservationId) {
        return reservationPersistencePort.findById(reservationId);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation getReservationByPayment(Payment payment) {
        return reservationPersistencePort.findByPayment(payment);
    }

    @Override
    @Transactional
    public void approveReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.RESERVED);
        reservationPersistencePort.save(reservation);
    }

    @Override
    @Transactional
    public void addReservation(Payment payment, Long showId, Member member) {
        Show show = showOperationPort.getShow(showId);
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.PENDING)
                .show(show)
                .member(member)
                .payment(payment)
                .build();
        reservationPersistencePort.save(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationOperationPort.getReservationById(reservationId);
        reservation.setStatus(ReservationStatus.CANCELED);
        reservationPersistencePort.save(reservation);
        paymentOperationPort.cancelPayment(reservation.getPayment());
    }
}
