package com.ticketing.solution.infrastructure.persistence.jpa;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowJpaRepository extends JpaRepository<Show, Long> {
    List<Show> findByMember(Member member);
}
