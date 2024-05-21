package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.member.MemberRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member addMember(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public void removeUserByEmail(String email) {
        memberJpaRepository.deleteByEmail(email);
    }

    @Override
    public boolean existByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberJpaRepository.findByEmail(email).orElseThrow();
    }

}
