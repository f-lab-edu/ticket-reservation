package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMember(PageRequest page, Member member);

    Optional<Reservation> findByPayment(Payment payment);
}
