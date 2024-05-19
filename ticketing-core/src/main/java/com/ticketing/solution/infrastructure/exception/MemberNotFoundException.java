package com.ticketing.solution.infrastructure.exception;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
