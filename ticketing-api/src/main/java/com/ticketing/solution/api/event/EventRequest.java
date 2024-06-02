package com.ticketing.solution.api.event;

import com.ticketing.solution.domain.event.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EventRequest(

        @NotNull(message = "이벤트 타입은 필수 입력 값입니다")
        EventType eventType,

        @NotBlank(message = "이벤트 이름은 필수 입력 값입니다")
        @Size(min = 2, max = 50, message = "이벤트 이름은 2자 이상 50자 이하이어야 합니다")
        String eventName,

        @NotBlank(message = "이벤트 설명은 필수 입력 값입니다")
        @Size(min = 2, max = 255, message = "이벤트 설명은 2자 이상 255자 이하이어야 합니다")
        String description,

        @NotBlank(message = "시작일은 필수 입력 값입니다")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "시작일은 YYYY-MM-DD 형식이어야 합니다")
        String startDate,

        @NotBlank(message = "종료일은 필수 입력 값입니다")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "종료일은 YYYY-MM-DD 형식이어야 합니다")
        String endDate,

        @NotBlank(message = "티켓팅일은 필수 입력 값입니다")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "티켓팅일은 YYYY-MM-DD 형식이어야 합니다")
        String ticketingDate

) { }
