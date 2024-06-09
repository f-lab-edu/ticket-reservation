package com.ticketing.solution.application.impl;

import com.ticketing.solution.application.ReservationFacade;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.PaymentService;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationService;
import com.ticketing.solution.domain.reservation.ReservationStatus;
import com.ticketing.solution.domain.show.Show;
import com.ticketing.solution.domain.show.ShowService;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationFacadeImpl implements ReservationFacade {
    private final ReservationService reservationService;

    private final PaymentService paymentService;

    private final ShowService showService;

    @Override
    @Transactional
    public void createReservation(Payment payment, Long showId, UserDetailsImpl userDetails) {
        Show show = showService.getShow(showId);
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.PENDING)
                .show(show)
                .member(userDetails.getMember())
                .payment(payment)
                .build();
        reservationService.addReservation(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationService.getReservation(reservationId);
        reservation.setStatus(ReservationStatus.CANCELED);
        paymentService.cancelPayment(reservation.getPayment());
    }

    @Override
    public Reservation getReservationByPayment(Payment payment) {
        return reservationService.getReservationByPayment(payment);
    }

    @Override
    public void approveReservation(Reservation reservation) {
        reservationService.approveReservation(reservation);
    }
}
