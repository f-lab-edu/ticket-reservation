package com.ticketing.solution.adapter.member;

import com.ticketing.solution.application.port.in.MemberOperationPort;
import com.ticketing.solution.domain.member.Member;
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
    private final MemberOperationPort memberOperationPort;
    private final MemberWebMapper memberWebMapper;

    @GetMapping("/member")
    public ResponseEntity<MemberResponse> getMember(@AuthenticationPrincipal UserDetails userDetails) {
        MemberResponse response = memberWebMapper.mapToMemberResponse(memberOperationPort.getMember(userDetails.getUsername()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/public/member")
    public ResponseEntity<MemberResponse> addMember(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        Member member = memberOperationPort.signUp(memberWebMapper.mapToMember(memberSignInRequest));
        MemberResponse response = memberWebMapper.mapToMemberResponse(member);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/member")
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateInfoRequest memberUpdateInfoRequest,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        memberOperationPort.updateMember(memberWebMapper.mapToMember(memberUpdateInfoRequest), userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member")
    public ResponseEntity<Void> removeMember(@AuthenticationPrincipal UserDetails userDetails) {
        memberOperationPort.withdraw(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
