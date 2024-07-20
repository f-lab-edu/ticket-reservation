package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.ReservationOperationPort;
import com.ticketing.solution.application.port.in.SeatOperationPort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationStatus;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationFacade implements ReservationOperationPort {

    private final ReservationService reservationService;

    private final PaymentService paymentService;

    private final ShowService showService;

    private final SeatOperationPort seatOperationPort;

    @Override
    @Transactional
    public void createReservation(Payment payment, Show show, Member member) {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.PENDING)
                .show(show)
                .member(member)
                .payment(payment)
                .build();
        reservationService.addReservation(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        reservation.setStatus(ReservationStatus.CANCELED);
        reservationService.addReservation(reservation);

        paymentService.cancelPayment(reservation.getPayment());
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @Override
    public Reservation getReservationByPayment(Payment payment) {
        return reservationService.getReservationByPayment(payment);
    }

    @Override
    public List<Reservation> getReservations(int page, Member member) {
        return reservationService.getReservations(page, member);
    }

    @Override
    public void approveReservation(Reservation reservation) {
        reservationService.approveReservation(reservation);
    }
}
