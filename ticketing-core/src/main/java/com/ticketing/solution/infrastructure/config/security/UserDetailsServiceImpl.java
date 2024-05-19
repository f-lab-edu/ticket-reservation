package com.ticketing.solution.infrastructure.config.security;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member loadMember = memberRepository.getMemberByEmail(email);
        return new UserDetailsImpl(loadMember);
    }
}
