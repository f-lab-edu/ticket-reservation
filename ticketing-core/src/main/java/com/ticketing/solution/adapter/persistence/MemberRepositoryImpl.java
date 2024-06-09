package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.MemberJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.MemberMapper;
import com.ticketing.solution.application.port.out.MemberRepository;
import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository { // MemberOperationPort, ....
    private final MemberJpaRepository memberJpaRepository;

    private final MemberMapper memberMapper;

    @Override
    public Member addMember(Member member) {
        var memberEntity = memberMapper.mapToEntity(member);
        return memberMapper.mapToDomain(memberJpaRepository.save(memberEntity));
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
        return memberMapper.mapToDomain(member);
    }

}
