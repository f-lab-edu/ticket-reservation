package com.ticketing.solution.domain.member;

public interface MemberRepository {
    Member addMember(Member member);

    Member getMemberByEmail(String email);

    void removeUserByEmail(String email);

    boolean existByEmail(String email);
}
