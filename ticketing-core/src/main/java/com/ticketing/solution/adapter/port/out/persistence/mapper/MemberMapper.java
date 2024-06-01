package com.ticketing.solution.adapter.port.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.ticketing.solution.adapter.port.out.persistence.entity.MemberJpaEntity;
import com.ticketing.solution.domain.member.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    public Member mapToDomain(MemberJpaEntity member) {
        return Member.builder()
                     .id(member.getId())
                     .email(member.getEmail())
                     .passwordHash(member.getPasswordHash())
                     .name(member.getName())
                     .phone(member.getPhone())
                     .address(member.getAddress())
                     .type(member.getType())
                     .createdDate(member.getCreatedDate())
                     .modifiedDate(member.getModifiedDate())
                     .build();
    }

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                              .id(member.getId())
                              .email(member.getEmail())
                              .passwordHash(member.getPasswordHash())
                              .name(member.getName())
                              .phone(member.getPhone())
                              .address(member.getAddress())
                              .type(member.getType())
                              .createdDate(member.getCreatedDate())
                              .modifiedDate(member.getModifiedDate())
                              .build();
    }
}
