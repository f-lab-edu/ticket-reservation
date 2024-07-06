package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.seat.SeatClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatClassJpaMapper {

    private final ShowJpaMapper showJpaMapper;

    public SeatClass mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.SeatClass seatClass) {
        return SeatClass.builder()
                .show(showJpaMapper.mapToDomain(seatClass.getShow()))
                .price(seatClass.getPrice())
                .name(seatClass.getName())
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.SeatClass mapToEntity(SeatClass seatClass) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.SeatClass.builder()
                .show(showJpaMapper.mapToEntity(seatClass.getShow()))
                .price(seatClass.getPrice())
                .name(seatClass.getName())
                .build();
    }
}
