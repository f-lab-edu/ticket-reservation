package com.ticketing.solution.application.port.in.exception;

public class PaymentFailedException extends CustomException {

    public PaymentFailedException(String message) {
        super(ErrorCode.PAYMENT_FAILED, message);
    }
}