package com.ticketing.solution.config.exception;

import com.ticketing.solution.application.service.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String errorMessage;
    private HttpStatus httpStatus;
    private String code;

    public ErrorResponse(HttpStatus status, String s) {
        this.errorMessage = s;
        this.httpStatus = status;
    }

    public ErrorResponse(ErrorCode code){
        this.errorMessage = code.getMessage();
        this.httpStatus = code.getStatus();
        this.code = code.getCode();
    }

}