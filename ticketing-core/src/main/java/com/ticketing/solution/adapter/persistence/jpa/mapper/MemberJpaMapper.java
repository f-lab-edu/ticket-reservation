package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberJpaMapper {

    public com.ticketing.solution.adapter.persistence.jpa.entity.Member mapToEntity(Member member) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Member.builder()
                .id(member.getId())
                .email(member.getEmail())
                .address(member.getAddress())
                .type(member.getType())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate())
                .name(member.getName())
                .phone(member.getPhone())
                .build();
    }

    public Member mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Member member) {
        return Member.builder()
                .id(member.getId())
                .email(member.getEmail())
                .address(member.getAddress())
                .type(member.getType())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate())
                .name(member.getName())
                .phone(member.getPhone())
                .build();
    }
}
