package com.ticketing.solution.domain.member;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member implements Serializable {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String phone;
    private String address;
    private MemberType type;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void update(Member member) {
        this.name = member.getName();
        this.phone = member.getPhone();
        this.address = member.getAddress();
    }
}
