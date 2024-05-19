package com.ticketing.solution.api.member;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping
    public ResponseEntity<MemberResponseDto> getMember(@AuthenticationPrincipal UserDetails userDetails) {
        MemberResponseDto response = memberMapper.mapToMemberResponse(memberService.getMember(userDetails.getUsername()));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(@Valid @RequestBody MemberSignInRequestDto memberSignInRequestDto) {
        Member member = memberService.signUp(memberMapper.mapToMember(memberSignInRequestDto));
        MemberResponseDto response = memberMapper.mapToMemberResponse(member);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateInfoRequestDto memberUpdateInfoRequestDto,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        memberService.updateMember(memberMapper.mapToMember(memberUpdateInfoRequestDto), userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeMember(@AuthenticationPrincipal UserDetails userDetails) {
        memberService.withdraw(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
