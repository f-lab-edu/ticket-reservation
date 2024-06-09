package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ShowMapper showMapper;
    private final PaymentMapper paymentMapper;

    public Reservation mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Reservation reservation) {
        return Reservation.builder()
                .id(reservation.getId())
                .createdDate(reservation.getCreatedDate())
                .status(reservation.getStatus())
                .show(showMapper.mapToDomain(reservation.getShow()))
                .payment(paymentMapper.mapToDomain(reservation.getPayment()))
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Reservation mapToEntity(Reservation reservation) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Reservation.builder()
                .id(reservation.getId())
                .createdDate(reservation.getCreatedDate())
                .status(reservation.getStatus())
                .show(showMapper.mapToEntity(reservation.getShow()))
                .payment(paymentMapper.mapToEntity(reservation.getPayment()))
                .build();
    }

    public List<Reservation> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.Reservation> reservations) {
        return reservations.stream()
                .map(this::mapToDomain)
                .toList();
    }
}
