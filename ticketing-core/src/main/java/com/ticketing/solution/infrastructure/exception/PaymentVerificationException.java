package com.ticketing.solution.infrastructure.exception;

public class PaymentVerificationException extends CustomException {
    public PaymentVerificationException() {
        super(ErrorCode.PAYMENT_VERIFICATION_FAILED);
    }
}