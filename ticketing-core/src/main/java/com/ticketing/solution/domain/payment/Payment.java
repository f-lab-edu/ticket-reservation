package com.ticketing.solution.domain.payment;

import com.ticketing.solution.domain.member.Member;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Payment {
    private Long id;
    private String merchantUid;
    private String impUid;
    private BigDecimal amount;
    private PaymentMethod method;
    private boolean approved;
    private Date dueDate;
    private Date paymentDate;
    private Date cancelDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Member member;

    public void update(Payment payment) {
        this.merchantUid = payment.getMerchantUid();
        this.impUid = payment.getImpUid();
        this.amount = payment.getAmount();
        this.method = payment.getMethod();
        this.approved = payment.isApproved();
        this.dueDate = payment.getDueDate();
        this.paymentDate = payment.getPaymentDate();
        this.cancelDate = payment.getCancelDate();
    }
}
