package com.ticketing.solution.application.service;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import com.ticketing.solution.domain.show.Show;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ReservationFacadeTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private ShowService showService;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    public void createReservationShouldCallAddReservation() {
        Payment payment = new Payment();
        Show show = new Show();
        Member member = new Member();

        when(showService.getShow(any())).thenReturn(show);

        reservationFacade.createReservation(payment, show, member);

        verify(reservationService, times(1)).addReservation(any(Reservation.class));
    }

    @Test
    public void cancelReservationShouldCallCancelPayment() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        Payment payment = new Payment();
        reservation.setPayment(payment);

        when(reservationService.getReservationById(reservationId)).thenReturn(reservation);

        reservationFacade.cancelReservation(reservationId);

        verify(paymentService, times(1)).cancelPayment(payment);
    }

    @Test
    public void getReservationByIdShouldReturnCorrectReservation() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();

        when(reservationService.getReservationById(reservationId)).thenReturn(reservation);

        Reservation result = reservationFacade.getReservationById(reservationId);

        assertEquals(reservation, result);
    }

    @Test
    public void getReservationByPaymentShouldReturnCorrectReservation() {
        Payment payment = new Payment();
        Reservation reservation = new Reservation();

        when(reservationService.getReservationByPayment(payment)).thenReturn(reservation);

        Reservation result = reservationFacade.getReservationByPayment(payment);

        assertEquals(reservation, result);
    }

    @Test
    public void getReservationsShouldReturnCorrectReservations() {
        int page = 1;
        Member member = new Member();

        reservationFacade.getReservations(page, member);

        verify(reservationService, times(1)).getReservations(page, member);
    }

    @Test
    public void approveReservationShouldCallApproveReservation() {
        Reservation reservation = new Reservation();

        reservationFacade.approveReservation(reservation);

        verify(reservationService, times(1)).approveReservation(reservation);
    }
}