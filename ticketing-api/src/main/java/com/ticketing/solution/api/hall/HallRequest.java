package com.ticketing.solution.api.hall;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record HallRequest (

    @NotBlank(message = "홀 이름은 필수 입력 값입니다")
    String name,

    @NotBlank(message = "홀 주소는 필수 입력 값입니다")
    @Pattern(regexp = "^[0-9a-zA-Z가-힣\\s]*$", message = "홀 주소는 한글, 영문, 숫자, 공백만 입력 가능합니다")
    String address,

    @NotBlank(message = "홀 전화번호는 필수 입력 값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "홀 전화번호는 02-1234-5678 형식이어야 합니다")
    int number,

    @NotBlank(message = "홀 수용인원은 필수 입력 값입니다")
    @Pattern(regexp = "^[0-9]*$", message = "홀 수용인원은 숫자만 입력 가능합니다")
    int capacity,

    String seatImage

) { }
