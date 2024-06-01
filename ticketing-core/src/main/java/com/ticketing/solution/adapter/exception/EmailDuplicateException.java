package com.ticketing.solution.adapter.exception;

public class EmailDuplicateException extends CustomException {
    public EmailDuplicateException() {
        super(ErrorCode.USER_EMAIL_DUPLICATION);
    }
}
