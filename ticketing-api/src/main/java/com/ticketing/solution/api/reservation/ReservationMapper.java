package com.ticketing.solution.api.reservation;

import com.ticketing.solution.api.pay.PaymentMapper;
import com.ticketing.solution.api.show.ShowMapper;
import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservationMapper {

    private final PaymentMapper paymentMapper;
    private final ShowMapper showMapper;

    public ReservationResponse mapToReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .reservationDate(reservation.getCreatedDate())
                .status(reservation.getStatus().name())
                .show(showMapper.mapToShowResponse(reservation.getShow()))
                .payment(paymentMapper.mapToPaymentResponse(reservation.getPayment()))
                .build();
    }

    public List<ReservationResponse> mapToReservationResponses(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::mapToReservationResponse)
                .toList();
    }
}
