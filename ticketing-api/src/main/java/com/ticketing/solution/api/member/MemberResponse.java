package com.ticketing.solution.api.member;

import lombok.Builder;

@Builder
public record MemberResponse(
        String email,
        String name,
        String phone,
        String address
) { }
