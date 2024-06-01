package com.ticketing.solution.application.exception;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
