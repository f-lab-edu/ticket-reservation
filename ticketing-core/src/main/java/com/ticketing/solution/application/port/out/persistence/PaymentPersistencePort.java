package com.ticketing.solution.application.port.out.persistence;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;

public interface PaymentPersistencePort {
    void save(Payment payment);

    Payment findByIdAndMember(Long id, Member member);

    Payment findByMerchantUid(String merchantUid);
}
