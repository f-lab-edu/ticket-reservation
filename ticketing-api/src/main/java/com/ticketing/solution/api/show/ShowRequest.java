package com.ticketing.solution.api.show;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record ShowRequest (

        @NotNull
        @Future
        LocalDate eventDate,

        @NotNull
        @Min(1)
        int maxOccupancy,

        @NotNull
        @Min(1)
        int playTime,

        @NotBlank
        @Length(min = 3, max = 255)
        String location,

        @NotNull
        Long eventId,

        @NotNull
        Long hallId
){
}
