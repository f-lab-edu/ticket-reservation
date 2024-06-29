package com.ticketing.solution.adapter.member;

import lombok.Builder;

@Builder
public record MemberResponse(
        String email,
        String name,
        String phone,
        String address
) { }
