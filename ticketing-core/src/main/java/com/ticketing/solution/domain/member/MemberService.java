package com.ticketing.solution.domain.member;

public interface MemberService {

    Member signUp(Member member);

    void withdraw(String email);

    Member getMember(String email);

    void updateMember(Member member, String email);
}
