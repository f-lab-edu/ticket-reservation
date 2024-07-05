package com.ticketing.solution.domain.reservation;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    RESERVED(0),
    CANCELED(1),
    PENDING(2);

    private final int dbValue;

    ReservationStatus(int dbValue) {
        this.dbValue = dbValue;
    }
}
