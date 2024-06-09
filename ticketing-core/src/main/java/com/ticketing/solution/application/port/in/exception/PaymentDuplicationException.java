package com.ticketing.solution.application.port.in.exception;

public class PaymentDuplicationException extends CustomException {

    public PaymentDuplicationException() {
        super(ErrorCode.PAYMENT_DUPLICATION);
    }
}