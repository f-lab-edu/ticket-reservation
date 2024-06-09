package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.application.port.in.PaymentService;
import com.ticketing.solution.application.port.in.ReservationService;
import com.ticketing.solution.application.port.in.ShowService;
import com.ticketing.solution.application.port.out.ReservationRepository;
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
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    private final PaymentService paymentService;

    private final ShowService showService;

    @Transactional
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservations(int page, Member member) {
        return reservationRepository.findByMember(page, member);
    }

    @Override
    @PostAuthorize("returnObject.member.email == authentication.name")
    @Transactional(readOnly = true)
    public Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation getReservationByPayment(Payment payment) {
        return reservationRepository.findByPayment(payment);
    }

    @Override
    @Transactional
    public void approveReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.RESERVED);
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public void createReservation(Payment payment, Long showId, Member member) {
        Show show = showService.getShow(showId);
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.PENDING)
                .show(show)
                .member(member)
                .payment(payment)
                .build();
        addReservation(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = getReservation(reservationId);
        reservation.setStatus(ReservationStatus.CANCELED);
        paymentService.cancelPayment(reservation.getPayment());
    }

}
