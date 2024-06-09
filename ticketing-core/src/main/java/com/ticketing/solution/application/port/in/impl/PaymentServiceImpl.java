package com.ticketing.solution.application.port.in.impl;

import com.ticketing.solution.application.port.in.PaymentService;
import com.ticketing.solution.application.port.in.ReservationService;
import com.ticketing.solution.application.port.in.exception.PaymentVerificationException;
import com.ticketing.solution.application.port.out.PaymentRepository;
import com.ticketing.solution.application.port.out.ThirdPartyPaymentService;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final ReservationService reservationService;

    private final ThirdPartyPaymentService thirdPartyPaymentService;

    @Override
    @Transactional
    public void save(Payment paymentResult) {
        paymentRepository.save(paymentResult);
    }

    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("returnObject.member.email == authentication.name")
    public Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByMerchantUid(String merchantUid) {
        return paymentRepository.findByMerchantUid(merchantUid);
    }

    @Override
    @PreAuthorize("#payment.member.email == authentication.name")
    @Transactional
    public void cancelPayment(Payment payment) {
        thirdPartyPaymentService.cancelPayment(payment.getImpUid());
        payment.setCancelDate(Date.from(Instant.from(LocalDate.now())));
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

        paymentRepository.save(payment);
        reservationService.createReservation(payment, command.showId(), member);
    }

    @Override
    @Transactional
    public void postPaymentProcess(String impUid){
        Payment portOnePaymentInfo = thirdPartyPaymentService.getPaymentInfo(impUid);
        Payment prePaymentInfo = getPaymentByMerchantUid(portOnePaymentInfo.getMerchantUid());
        verifyPayment(prePaymentInfo, portOnePaymentInfo);
        approvePayment(prePaymentInfo);
        approveReservation(prePaymentInfo);
    }

    private void verifyPayment(Payment prePaymentInfo, Payment paymentInfo) {
        boolean isInvalidAmount = paymentInfo.getAmount().equals(prePaymentInfo.getAmount());
        if (isInvalidAmount) {
            thirdPartyPaymentService.cancelPayment(paymentInfo.getImpUid());
            throw new PaymentVerificationException();
        }
    }

    private void approvePayment(Payment payment) {
        payment.setApproved(true);
        paymentRepository.save(payment);
    }

    private void approveReservation(Payment payment) {
        Reservation reservation = reservationService.getReservationByPayment(payment);
        reservationService.approveReservation(reservation);
    }
}
