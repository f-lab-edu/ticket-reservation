package com.ticketing.solution.application;

import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;

public interface PaymentFacade {

    Payment getPayment(Long paymentId);

    void postPaymentProcess(String impUid);

    void prePaymentProcess(ProcessPrePaymentCommand command, UserDetailsImpl userDetails);

}
