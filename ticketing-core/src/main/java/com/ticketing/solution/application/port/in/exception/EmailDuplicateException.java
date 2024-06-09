package com.ticketing.solution.application.port.in.exception;

public class EmailDuplicateException extends CustomException {
    public EmailDuplicateException() {
        super(ErrorCode.USER_EMAIL_DUPLICATION);
    }
}
