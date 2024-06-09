package com.ticketing.solution.application.operation;

import com.ticketing.solution.application.port.in.PaymentOperationPort;
import com.ticketing.solution.application.port.in.ReservationOperationPort;
import com.ticketing.solution.application.port.in.exception.PaymentVerificationException;
import com.ticketing.solution.application.port.out.persistence.PaymentPersistencePort;
import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class PaymentOperation implements PaymentOperationPort {
    private final PaymentPersistencePort paymentPersistencePort;

    private final ReservationOperationPort reservationOperationPort;

    private final ThirdPartyPaymentPort thirdPartyPaymentPort;

    @Override
    @Transactional
    public void save(Payment paymentResult) {
        paymentPersistencePort.save(paymentResult);
    }

    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("returnObject.member.email == authentication.name")
    public Payment getPaymentById(Long paymentId) {
        return paymentPersistencePort.findById(paymentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByMerchantUid(String merchantUid) {
        return paymentPersistencePort.findByMerchantUid(merchantUid);
    }

    @Override
    @PreAuthorize("#payment.member.email == authentication.name")
    @Transactional
    public void cancelPayment(Payment payment) {
        thirdPartyPaymentPort.cancelPayment(payment.getImpUid());
        payment.setCancelDate(Date.from(Instant.from(LocalDate.now())));
        paymentPersistencePort.save(payment);
    }

    @Override
    @Transactional
    public void prePaymentProcess(ProcessPrePaymentCommand command, Member member) {
        Payment payment = Payment.builder()
                .approved(false)
                .merchantUid(command.merchantUid())
                .amount(command.amount())
                .member(member)
                .build();

        paymentPersistencePort.save(payment);
        reservationOperationPort.addReservation(payment, command.showId(), member);
    }

    @Override
    @Transactional
    public void postPaymentProcess(String impUid){
        Payment portOnePaymentInfo = thirdPartyPaymentPort.getPaymentInfo(impUid);
        Payment prePaymentInfo = getPaymentByMerchantUid(portOnePaymentInfo.getMerchantUid());
        verifyPayment(prePaymentInfo, portOnePaymentInfo);
        approvePayment(prePaymentInfo);
        approveReservation(prePaymentInfo);
    }

    private void verifyPayment(Payment prePaymentInfo, Payment paymentInfo) {
        boolean isInvalidAmount = paymentInfo.getAmount().equals(prePaymentInfo.getAmount());
        if (isInvalidAmount) {
            thirdPartyPaymentPort.cancelPayment(paymentInfo.getImpUid());
            throw new PaymentVerificationException();
        }
    }

    private void approvePayment(Payment payment) {
        payment.setApproved(true);
        paymentPersistencePort.save(payment);
    }

    private void approveReservation(Payment payment) {
        Reservation reservation = reservationOperationPort.getReservationByPayment(payment);
        reservationOperationPort.approveReservation(reservation);
    }
}
