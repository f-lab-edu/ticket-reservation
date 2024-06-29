package com.ticketing.solution.adapter.reservation;

import com.ticketing.solution.adapter.pay.PaymentWebMapper;
import com.ticketing.solution.adapter.show.ShowWebMapper;
import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservationWebMapper {

    private final PaymentWebMapper paymentWebMapper;
    private final ShowWebMapper showWebMapper;

    public ReservationResponse mapToReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getCreatedDate())
                .status(reservation.getStatus().name())
                .show(showWebMapper.mapToShowResponse(reservation.getShow()))
                .payment(paymentWebMapper.mapToPaymentResponse(reservation.getPayment()))
                .build();
    }

    public List<ReservationResponse> mapToReservationResponses(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::mapToReservationResponse)
                .toList();
    }
}
