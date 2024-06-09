package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.Member;
import com.ticketing.solution.adapter.persistence.jpa.entity.Payment;
import com.ticketing.solution.adapter.persistence.jpa.entity.Reservation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMember(PageRequest page, Member member);

    Optional<Reservation> findByPayment(Payment payment);
}
