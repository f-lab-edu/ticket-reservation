package com.ticketing.solution.application.port.in.exception;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
