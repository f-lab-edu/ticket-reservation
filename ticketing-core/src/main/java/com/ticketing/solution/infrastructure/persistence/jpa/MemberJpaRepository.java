package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
