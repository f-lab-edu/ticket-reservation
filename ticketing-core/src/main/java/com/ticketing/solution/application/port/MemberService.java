package com.ticketing.solution.application.port;

import com.ticketing.solution.application.port.in.MemberUseCasePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.application.port.out.MemberOperationPort;
import com.ticketing.solution.adapter.exception.EmailDuplicateException;
import com.ticketing.solution.adapter.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService implements MemberUseCasePort {
    private final MemberOperationPort memberOperationPort;

    @Override
//    @Transactional -> 트랜잭션은 adapter 단의 관심사이므로 memberOperationPort implementation에서 처리
    public Member signUp(Member member) {
        if (memberOperationPort.existByEmail(member.getEmail())) {
            throw new EmailDuplicateException();
        }
        return memberOperationPort.addMember(member);
    }

    @Override
//    @Transactional -> 트랜잭션은 adapter 단의 관심사이므로 memberOperationPort implementation에서 처리
    public void withdraw(String email) {
        if (memberOperationPort.existByEmail(email)) {
            throw new MemberNotFoundException();
        }
        memberOperationPort.removeUserByEmail(email);
    }

    @Override
//    @Transactional -> 트랜잭션은 adapter 단의 관심사이므로 memberOperationPort implementation에서 처리
    public Member getMember(String email) {
        return memberOperationPort.getMemberByEmail(email);
    }

    @Override
//    @Transactional -> 트랜잭션은 adapter 단의 관심사이므로 memberOperationPort implementation에서 처리
    public void updateMember(Member member, String email) {
        Member dbMember = memberOperationPort.getMemberByEmail(email);
        dbMember.update(member);
    }
}
