package com.ticketing.solution.adapter.exception;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
