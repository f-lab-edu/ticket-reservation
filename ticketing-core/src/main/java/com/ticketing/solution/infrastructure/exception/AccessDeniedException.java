package com.ticketing.solution.infrastructure.exception;

public class AccessDeniedException extends CustomException{
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
