package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.MemberJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.MemberJpaMapper;
import com.ticketing.solution.application.port.out.persistence.MemberPersistencePort;
import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberJpaAdapter implements MemberPersistencePort {
    private final MemberJpaRepository memberJpaRepository;

    private final MemberJpaMapper memberJpaMapper;

    @Override
    public Member addMember(Member member) {
        var memberEntity = memberJpaMapper.mapToEntity(member);
        return memberJpaMapper.mapToDomain(memberJpaRepository.save(memberEntity));
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
        var member = memberJpaRepository.findByEmail(email).orElseThrow();
        return memberJpaMapper.mapToDomain(member);
    }
}
