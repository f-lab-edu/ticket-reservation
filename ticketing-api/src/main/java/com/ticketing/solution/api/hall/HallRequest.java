package com.ticketing.solution.api.hall;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HallRequest (

    @NotBlank(message = "홀 이름은 필수 입력 값입니다")
    @Size(max = 255, message = "홀 이름은 255자를 초과할 수 없습니다")
    String name,

    @NotBlank(message = "홀 주소는 필수 입력 값입니다")
    @Size(max = 255, message = "홀 주소는 255자를 초과할 수 없습니다")
    @Pattern(regexp = "^[0-9a-zA-Z가-힣\\s]*$", message = "홀 주소는 한글, 영문, 숫자, 공백만 입력 가능합니다")
    String address,

    @NotBlank(message = "홀 전화번호는 필수 입력 값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "홀 전화번호는 02-1234-5678 형식이어야 합니다")
    String number,

    @NotBlank(message = "홀 수용인원은 필수 입력 값입니다")
    @Pattern(regexp = "^[0-9]*$", message = "홀 수용인원은 숫자만 입력 가능합니다")
    int capacity,

    @Size(max = 2048, message = "이미지 URL은 2048자를 초과할 수 없습니다")
    @Pattern(regexp = "^(https?://).+", message = "유효한 URL 형식이어야 합니다")
    String seatImage

) { }
