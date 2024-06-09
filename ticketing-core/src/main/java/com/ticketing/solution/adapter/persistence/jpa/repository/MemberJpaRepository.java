package com.ticketing.solution.adapter.persistence.jpa.repository;

import com.ticketing.solution.adapter.persistence.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
