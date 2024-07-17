package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.seat.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatJpaMapper {

    private final ShowJpaMapper showJpaMapper;
    private final SeatClassJpaMapper showClassJpaMapper;
    private final HallJpaMapper hallJpaMapper;

    public Seat mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Seat seat) {
        return Seat.builder()
                .id(seat.getId())
                .show(showJpaMapper.mapToDomain(seat.getShow()))
                .seatClass(showClassJpaMapper.mapToDomain(seat.getSeatClass()))
                .capacity(seat.getCapacity())
                .hall(hallJpaMapper.mapToDomain(seat.getHall()))
                .build();
    }

    public List<Seat> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.Seat> seats) {
        return seats.stream().map(this::mapToDomain).toList();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Seat mapToEntity(Seat seat) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Seat.builder()
                .id(seat.getId())
                .show(showJpaMapper.mapToEntity(seat.getShow()))
                .seatClass(showClassJpaMapper.mapToEntity(seat.getSeatClass()))
                .capacity(seat.getCapacity())
                .hall(hallJpaMapper.mapToEntity(seat.getHall()))
                .build();
    }
}
