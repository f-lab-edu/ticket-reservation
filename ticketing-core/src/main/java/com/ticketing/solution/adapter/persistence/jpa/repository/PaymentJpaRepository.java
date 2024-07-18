package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.Member;
import com.ticketing.solution.adapter.persistence.jpa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByMerchantUid(String impUid);

    Optional<Payment> findByIdAndMember(Long id, Member member);
}
