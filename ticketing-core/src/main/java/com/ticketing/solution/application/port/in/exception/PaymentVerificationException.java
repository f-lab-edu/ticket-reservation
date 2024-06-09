package com.ticketing.solution.application.port.in.exception;

public class PaymentVerificationException extends CustomException {
    public PaymentVerificationException() {
        super(ErrorCode.PAYMENT_VERIFICATION_FAILED);
    }
}