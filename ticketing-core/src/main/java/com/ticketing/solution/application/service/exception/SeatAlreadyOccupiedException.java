package com.ticketing.solution.application.service.exception;

public class SeatAlreadyOccupiedException extends CustomException {
    public SeatAlreadyOccupiedException() {
        super(ErrorCode.SEAT_ALREADY_OCCUPIED);
    }
}
