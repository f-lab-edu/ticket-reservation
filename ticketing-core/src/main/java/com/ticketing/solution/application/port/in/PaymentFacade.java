package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;

public interface PaymentFacade {

    Payment getPayment(Long paymentId);

    void postPaymentProcess(String impUid);

    void prePaymentProcess(ProcessPrePaymentCommand command, Member member);

}
