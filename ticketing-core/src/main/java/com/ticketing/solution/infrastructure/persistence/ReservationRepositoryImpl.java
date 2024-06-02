package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpaRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationJpaRepository.findById(reservationId).orElseThrow();
    }

    @Override
    public List<Reservation> findByMember(int page, Member member) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "id"));
        return reservationJpaRepository.findByMember(pageRequest, member);
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationJpaRepository.deleteById(reservationId);
    }

    @Override
    public Reservation findByPayment(Payment payment) {
        return reservationJpaRepository.findByPayment(payment).orElseThrow();
    }
}
