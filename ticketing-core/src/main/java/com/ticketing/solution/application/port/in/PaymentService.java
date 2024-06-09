package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;

public interface PaymentService {
    void save(Payment paymentResult);

    Payment getPayment(Long paymentId);

    Payment getPaymentByMerchantUid(String merchantUid);

    void cancelPayment(Payment payment);

    void postPaymentProcess(String impUid);

    void prePaymentProcess(ProcessPrePaymentCommand command, Member member);
}
