package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.PaymentOperationPort;
import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.application.service.exception.PaymentVerificationException;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentFacade implements PaymentOperationPort {

    private final PaymentService paymentService;

    private final ReservationFacade reservationFacade;

    private final ThirdPartyPaymentPort thirdPartyPaymentService;


    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentById(Long paymentId, Member member) {
        return paymentService.getPaymentByIdAndMember(paymentId, member);
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
        paymentService.save(payment);
        reservationFacade.createReservation(payment, command.showId(), member);
    }

    @Override
    @Transactional
    public void postPaymentProcess(String impUid){
        Payment portOnePaymentInfo = thirdPartyPaymentService.getPaymentInfo(impUid);
        Payment prePaymentInfo = paymentService.getPaymentByMerchantUid(portOnePaymentInfo.getMerchantUid());
        if (!verifyPayment(prePaymentInfo, portOnePaymentInfo)){
            cancelPayment(impUid, prePaymentInfo.getId());
            throw new PaymentVerificationException();
        }
        approvePayment(prePaymentInfo);
    }

    public boolean verifyPayment(Payment prePaymentInfo, Payment paymentInfo) {
        return paymentInfo.getAmount().equals(prePaymentInfo.getAmount());
    }

    @Override
    @Transactional
    public void cancelPayment(String impUid, Long paymentId) {
        thirdPartyPaymentService.cancelPayment(impUid);
        reservationFacade.cancelReservation(paymentId);
    }

    private void approvePayment(Payment payment) {
        Reservation reservation = reservationFacade.getReservationByPayment(payment);
        reservationFacade.approveReservation(reservation);
        payment.setApproved(true);
        paymentService.save(payment);
    }
}
