package com.ticketing.solution.adapter.member;

import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberWebMapper {

    private final PasswordEncoder passwordEncoder;

    public Member mapToMember(MemberSignInRequest memberSignInRequest) {
        return Member.builder()
                .email(memberSignInRequest.email())
                .name(memberSignInRequest.name())
                .phone(memberSignInRequest.phone())
                .passwordHash(passwordEncoder.encode(memberSignInRequest.password()))
                .address(memberSignInRequest.address())
                .build();
    }

    public Member mapToMember(MemberUpdateInfoRequest memberUpdateRequestDto) {
        return Member.builder()
                .name(memberUpdateRequestDto.name())
                .phone(memberUpdateRequestDto.phone())
                .address(memberUpdateRequestDto.address())
                .build();
    }

    public MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
    }
}
