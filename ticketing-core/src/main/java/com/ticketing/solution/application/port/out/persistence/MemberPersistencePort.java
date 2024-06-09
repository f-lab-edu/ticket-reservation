package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.member.Member;


public interface MemberPersistencePort {
    Member addMember(Member member);

    Member getMemberByEmail(String email);

    void removeUserByEmail(String email);

    boolean existByEmail(String email);
}
