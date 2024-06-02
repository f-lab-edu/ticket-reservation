package com.ticketing.solution.application;

import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;

import java.math.BigDecimal;

public interface PaymentFacade {

    void postPaymentProcess(String impUid);

    void prePaymentProcess(String merchantUid, Long showId, BigDecimal amount, UserDetailsImpl userDetails);

}
