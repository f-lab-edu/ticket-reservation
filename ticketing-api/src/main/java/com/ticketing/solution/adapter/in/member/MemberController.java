package com.ticketing.solution.adapter.in.member;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.application.port.in.MemberUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberUseCasePort memberUseCasePort;
    private final MemberMapper memberMapper;

    @GetMapping("/member")
    public ResponseEntity<MemberResponse> getMember(@AuthenticationPrincipal UserDetails userDetails) {
        MemberResponse response = memberMapper.mapToMemberResponse(memberUseCasePort.getMember(userDetails.getUsername()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/public/member")
    public ResponseEntity<MemberResponse> addMember(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        Member member = memberUseCasePort.signUp(memberMapper.mapToMember(memberSignInRequest));
        MemberResponse response = memberMapper.mapToMemberResponse(member);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/member")
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateInfoRequest memberUpdateInfoRequest,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        memberUseCasePort.updateMember(memberMapper.mapToMember(memberUpdateInfoRequest), userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member")
    public ResponseEntity<Void> removeMember(@AuthenticationPrincipal UserDetails userDetails) {
        memberUseCasePort.withdraw(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
