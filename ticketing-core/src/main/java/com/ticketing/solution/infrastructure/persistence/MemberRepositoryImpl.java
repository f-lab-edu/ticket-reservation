package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.member.MemberRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

}
