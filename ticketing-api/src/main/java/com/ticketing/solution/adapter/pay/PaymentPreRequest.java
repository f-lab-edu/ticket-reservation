package com.ticketing.solution.adapter.pay;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Builder
public record PaymentPreRequest(

        @NotBlank
        @Length(max = 255)
        String merchantUid,

        @NotNull
        Long showId,

        @NotNull
        @Positive
        BigDecimal amount
) {
}
