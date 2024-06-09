package com.ticketing.solution.application.impl;

import com.ticketing.solution.application.PaymentFacade;
import com.ticketing.solution.application.ReservationFacade;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.PaymentService;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.reservation.ReservationService;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import com.ticketing.solution.infrastructure.exception.PaymentVerificationException;
import com.ticketing.solution.infrastructure.thirdPartyPayment.portOne.PortOneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class PaymentFacadeImpl implements PaymentFacade {

    private final PaymentService paymentService;

    private final ReservationFacade reservationFacade;

    private final PortOneService portOneService;

    @Override
    @Transactional(readOnly = true)
    public Payment getPayment(Long paymentId) {
        return paymentService.getPayment(paymentId);
    }

    @Override
    @Transactional
    public void prePaymentProcess(ProcessPrePaymentCommand command, UserDetailsImpl userDetails) {
        Payment payment = Payment.builder()
                .approved(false)
                .merchantUid(command.merchantUid())
                .amount(command.amount())
                .member(userDetails.getMember())
                .build();

        paymentService.save(payment);
        reservationFacade.createReservation(payment, showId, userDetails);
    }

    @Override
    @Transactional
    public void postPaymentProcess(String impUid){
        Payment portOnePaymentInfo = portOneService.getPaymentInfo(impUid);
        Payment prePaymentInfo = paymentService.getPaymentByMerchantUid(portOnePaymentInfo.getMerchantUid());
        verifyPayment(prePaymentInfo, portOnePaymentInfo);
        approvePayment(prePaymentInfo, portOnePaymentInfo);
        approveReservation(prePaymentInfo);
    }

    private void verifyPayment(Payment prePaymentInfo, Payment paymentInfo) {
        boolean isInvalidAmount = paymentInfo.getAmount().equals(prePaymentInfo.getAmount());
        if (isInvalidAmount) {
            portOneService.cancelPayment(paymentInfo.getImpUid());
            throw new PaymentVerificationException();
        }
    }

    private void approvePayment(Payment payment, Payment paymentInfo) {
        payment.setApproved(true);
    }

    private void approveReservation(Payment payment) {
        Reservation reservation = reservationFacade.getReservationByPayment(payment);
        reservationFacade.approveReservation(reservation);
    }
}
