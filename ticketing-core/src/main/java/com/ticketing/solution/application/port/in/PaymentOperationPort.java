package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;

public interface PaymentOperationPort {
    Payment getPaymentById(Long paymentId);

    void postPaymentProcess(String impUid);

    void prePaymentProcess(ProcessPrePaymentCommand command, Member member);

    void cancelPayment(String impUid, Long paymentId);
}
