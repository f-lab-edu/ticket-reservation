package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;

public interface MemberService {

    Member signUp(Member member);

    void withdraw(String email);

    Member getMember(String email);

    void updateMember(Member member, String email);
}
