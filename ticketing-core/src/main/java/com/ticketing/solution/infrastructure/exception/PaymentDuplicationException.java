package com.ticketing.solution.infrastructure.exception;

public class PaymentDuplicationException extends CustomException {

    public PaymentDuplicationException() {
        super(ErrorCode.PAYMENT_DUPLICATION);
    }
}