package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
