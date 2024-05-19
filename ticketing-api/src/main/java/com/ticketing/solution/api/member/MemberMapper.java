package com.ticketing.solution.api.member;

import com.ticketing.solution.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member mapToMember(MemberSignInRequestDto memberSignInRequestDto) {
        return Member.builder()
                .email(memberSignInRequestDto.email())
                .name(memberSignInRequestDto.name())
                .phone(memberSignInRequestDto.phone())
                .passwordHash(memberSignInRequestDto.password())
                .address(memberSignInRequestDto.address())
                .build();
    }

    public Member mapToMember(MemberUpdateInfoRequestDto memberUpdateRequestDto) {
        return Member.builder()
                .name(memberUpdateRequestDto.name())
                .phone(memberUpdateRequestDto.phone())
                .address(memberUpdateRequestDto.address())
                .build();
    }

    public MemberResponseDto mapToMemberResponse(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
    }
}
