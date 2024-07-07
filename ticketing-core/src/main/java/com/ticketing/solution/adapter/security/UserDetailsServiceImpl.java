package com.ticketing.solution.adapter.security;

import com.ticketing.solution.application.port.out.persistence.MemberPersistencePort;
import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final MemberPersistencePort memberPersistencePort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberPersistencePort.getMemberByEmail(email);
        return new UserDetailsImpl(member);
    }
}
