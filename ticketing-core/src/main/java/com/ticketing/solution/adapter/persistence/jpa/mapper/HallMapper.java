package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HallMapper {

    public Hall mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Hall hall) {
        return Hall.builder()
                .id(hall.getId())
                .name(hall.getName())
                .number(hall.getNumber())
                .seatImage(hall.getSeatImage())
                .address(hall.getAddress())
                .capacity(hall.getCapacity())
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Hall mapToEntity(Hall hall) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Hall.builder()
                .id(hall.getId())
                .name(hall.getName())
                .number(hall.getNumber())
                .seatImage(hall.getSeatImage())
                .address(hall.getAddress())
                .capacity(hall.getCapacity())
                .build();
    }
}
