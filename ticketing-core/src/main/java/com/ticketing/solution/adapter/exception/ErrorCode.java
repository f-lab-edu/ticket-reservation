package com.ticketing.solution.adapter.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C100", "올바르지 않은 값입니다."),
    UNFILLED_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C200", "필요한 값을 전부 입력하지 않았습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C300", "페이지를 찾을 수 없습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C400", "접속이 거부되었습니다."),
    REQUEST_DENIED(HttpStatus.NOT_ACCEPTABLE, "C500", "허용되지 않은 IP입니다."),
    SQL_CONFLICT(HttpStatus.CONFLICT, "C600", "중복된 값이 존재합니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U100", "사용자를 찾을 수 없습니다."),
    USER_EMAIL_DUPLICATION(HttpStatus.CONFLICT, "U200", "이미 사용중인 이메일입니다.");

    private HttpStatus status;
    private String code;
    private String message;


    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
