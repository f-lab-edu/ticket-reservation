package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationJpaMapper {

    private final ShowJpaMapper showJpaMapper;
    private final PaymentJpaMapper paymentJpaMapper;

    public Reservation mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Reservation reservation) {
        return Reservation.builder()
                .id(reservation.getId())
                .createdDate(reservation.getCreatedDate())
                .status(reservation.getStatus())
                .show(showJpaMapper.mapToDomain(reservation.getShow()))
                .payment(paymentJpaMapper.mapToDomain(reservation.getPayment()))
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Reservation mapToEntity(Reservation reservation) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Reservation.builder()
                .id(reservation.getId())
                .createdDate(reservation.getCreatedDate())
                .status(reservation.getStatus())
                .show(showJpaMapper.mapToEntity(reservation.getShow()))
                .payment(paymentJpaMapper.mapToEntity(reservation.getPayment()))
                .build();
    }

    public List<Reservation> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.Reservation> reservations) {
        return reservations.stream()
                .map(this::mapToDomain)
                .toList();
    }
}
