package com.ticketing.solution.application.port.in.exception;

public class AccessDeniedException extends CustomException{
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
