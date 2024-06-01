package com.ticketing.solution.application.port.out;

import com.ticketing.solution.domain.member.Member;

public interface MemberOperationPort {
    Member addMember(Member member);

    Member getMemberByEmail(String email);

    void removeUserByEmail(String email);

    boolean existByEmail(String email);
}
