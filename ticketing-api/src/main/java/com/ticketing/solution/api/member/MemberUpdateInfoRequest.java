package com.ticketing.solution.api.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MemberUpdateInfoRequest(

        @NotBlank(message = "이름은 필수 입력 값입니다")
        @Size(min = 2, max = 50, message = "이름은 2자 이상 50자 이하이어야 합니다")
        String name,

        @NotBlank(message = "전화번호는 필수 입력 값입니다")
        @Pattern(regexp = "^[0-9]{10,15}$", message = "전화번호는 10자 이상 15자 이하의 숫자만 가능합니다")
        String phone,

        @NotBlank(message = "주소는 필수 입력 값입니다")
        @Size(max = 255, message = "주소는 255자 이하이어야 합니다")
        String address
) {
}
