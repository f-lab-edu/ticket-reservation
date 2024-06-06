package com.ticketing.solution.api.pay;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record PaymentPostRequest(
        @NotBlank
        @Length(min = 1, max = 255)
        String imp_uid,

        @NotBlank
        @Length(min = 1, max = 255)
        String status,

        @Length(max = 255)
        String cancellation_id,

        @NotBlank
        @Length(min = 1, max = 255)
        String name
) {
}
