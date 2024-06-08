package com.ticketing.solution.domain.reservation;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    @Transactional
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservations(int page, UserDetailsImpl userDetails) {
        return reservationRepository.findByMember(page, userDetails.getMember());
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
    }

}
