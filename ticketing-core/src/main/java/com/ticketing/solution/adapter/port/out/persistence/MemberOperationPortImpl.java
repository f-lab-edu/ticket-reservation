package com.ticketing.solution.adapter.port.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.solution.adapter.port.out.persistence.jpa.MemberJpaRepository;
import com.ticketing.solution.adapter.port.out.persistence.mapper.MemberMapper;
import com.ticketing.solution.application.port.out.MemberOperationPort;
import com.ticketing.solution.domain.member.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberOperationPortImpl implements MemberOperationPort {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public Member addMember(Member member) {
        var memberEntity = memberMapper.mapToJpaEntity(member);
        var result = memberJpaRepository.save(memberEntity);
        return memberMapper.mapToDomain(result);
    }

    @Override
    @Transactional
    public void removeUserByEmail(String email) {
        memberJpaRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public boolean existByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Member getMemberByEmail(String email) {
        return memberJpaRepository.findByEmail(email).orElseThrow();
    }

}
