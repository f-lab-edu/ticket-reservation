package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.seat.SeatOccupy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatOccupyJpaMapper {

    private final SeatJpaMapper seatJpaMapper;
    private final MemberJpaMapper memberJpaMapper;

    public SeatOccupy mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.SeatOccupy seatOccupy) {
        return SeatOccupy.builder()
                .id(seatOccupy.getId())
                .seat(seatJpaMapper.mapToDomain(seatOccupy.getSeat()))
                .member(memberJpaMapper.mapToDomain(seatOccupy.getMember()))
                .build();
    }

    public List<SeatOccupy> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.SeatOccupy> seatOccupies) {
        return seatOccupies.stream()
                .map(this::mapToDomain)
                .toList();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.SeatOccupy mapToEntity(SeatOccupy seatOccupy) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.SeatOccupy.builder()
                .id(seatOccupy.getId())
                .seat(seatJpaMapper.mapToEntity(seatOccupy.getSeat()))
                .member(memberJpaMapper.mapToEntity(seatOccupy.getMember()))
                .build();
    }


}
