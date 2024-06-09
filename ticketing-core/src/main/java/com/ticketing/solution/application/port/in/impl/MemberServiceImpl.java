package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.application.port.in.MemberService;
import com.ticketing.solution.application.port.in.exception.EmailDuplicateException;
import com.ticketing.solution.application.port.in.exception.MemberNotFoundException;
import com.ticketing.solution.application.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member signUp(Member member) {
        if (memberRepository.existByEmail(member.getEmail())) {
            throw new EmailDuplicateException();
        }
        return memberRepository.addMember(member);
    }

    @Override
    @Transactional
    public void withdraw(String email) {
        if (memberRepository.existByEmail(email)) {
            throw new MemberNotFoundException();
        }
        memberRepository.removeUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String email) {
        return memberRepository.getMemberByEmail(email);
    }

    @Override
    @Transactional
    public void updateMember(Member member, String email) {
        Member dbMember = memberRepository.getMemberByEmail(email);
        dbMember.update(member);
    }
}
