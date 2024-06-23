package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.application.service.exception.PaymentVerificationException;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.domain.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentFacadeTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private ReservationFacade reservationFacade;

    @Mock
    private ThirdPartyPaymentPort thirdPartyPaymentService;

    @InjectMocks
    private PaymentFacade paymentFacade;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .merchantUid("impUid")
                .amount(BigDecimal.valueOf(2000))
                .build();
    }

    @Test
    void prePaymentProcessCreatesPaymentAndReservation() {
        Member member = new Member();
        ProcessPrePaymentCommand command = new ProcessPrePaymentCommand("impUid", 1L, BigDecimal.valueOf(1000));

        paymentFacade.prePaymentProcess(command, member);

        verify(paymentService, times(1)).save(argThat(payment -> payment.getMerchantUid().equals(command.merchantUid())));
        verify(reservationFacade, times(1)).createReservation(argThat(payment -> payment.getMerchantUid().equals(command.merchantUid())), eq(command.showId()), eq(member));
    }

    @Test
    void postPaymentProcessVerifiesAndApprovesPaymentAndReservation() {
        Reservation reservation = new Reservation();

        when(thirdPartyPaymentService.getPaymentInfo("impUid")).thenReturn(payment);
        when(paymentService.getPaymentByMerchantUid("impUid")).thenReturn(payment);
        when(reservationFacade.getReservationByPayment(payment)).thenReturn(reservation);

        paymentFacade.postPaymentProcess("impUid");

        verify(paymentService, times(1)).save(payment);
        verify(reservationFacade, times(1)).approveReservation(reservation);
    }

    @Test
    void postPaymentProcessThrowsExceptionWhenPaymentIsInvalid() {
        Payment invalidPayment = Payment.builder()
                .merchantUid("impUid")
                .amount(BigDecimal.valueOf(1000))
                .build();

        when(thirdPartyPaymentService.getPaymentInfo("impUid")).thenReturn(invalidPayment);
        when(paymentService.getPaymentByMerchantUid("impUid")).thenReturn(payment);

        assertThrows(PaymentVerificationException.class, () -> paymentFacade.postPaymentProcess("impUid"));
    }
}