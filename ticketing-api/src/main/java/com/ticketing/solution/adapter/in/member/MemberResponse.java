package com.ticketing.solution.adapter.in.member;

import lombok.Builder;

@Builder
public record MemberResponse(
        String email,
        String name,
        String phone,
        String address
) { }
