package com.ticketing.solution.application.service.exception;

public class PaymentDuplicationException extends CustomException {

    public PaymentDuplicationException() {
        super(ErrorCode.PAYMENT_DUPLICATION);
    }
}