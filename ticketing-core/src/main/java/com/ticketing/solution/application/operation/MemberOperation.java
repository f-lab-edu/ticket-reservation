package com.ticketing.solution.application.operation;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.application.port.in.MemberOperationPort;
import com.ticketing.solution.application.port.in.exception.EmailDuplicateException;
import com.ticketing.solution.application.port.in.exception.MemberNotFoundException;
import com.ticketing.solution.application.port.out.persistence.MemberPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberOperation implements MemberOperationPort {
    private final MemberPersistencePort memberPersistencePort;

    @Override
    @Transactional
    public Member signUp(Member member) {
        if (memberPersistencePort.existByEmail(member.getEmail())) {
            throw new EmailDuplicateException();
        }
        return memberPersistencePort.addMember(member);
    }

    @Override
    @Transactional
    public void withdraw(String email) {
        if (memberPersistencePort.existByEmail(email)) {
            throw new MemberNotFoundException();
        }
        memberPersistencePort.removeUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String email) {
        return memberPersistencePort.getMemberByEmail(email);
    }

    @Override
    @Transactional
    public void updateMember(Member member, String email) {
        Member dbMember = memberPersistencePort.getMemberByEmail(email);
        dbMember.update(member);
        memberPersistencePort.addMember(dbMember);
    }
}
