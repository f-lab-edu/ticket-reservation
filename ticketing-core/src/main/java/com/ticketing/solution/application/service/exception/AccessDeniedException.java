package com.ticketing.solution.application.service.exception;

public class AccessDeniedException extends CustomException{
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
