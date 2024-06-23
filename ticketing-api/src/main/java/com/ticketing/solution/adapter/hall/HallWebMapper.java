package com.ticketing.solution.adapter.hall;

import com.ticketing.solution.domain.hall.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HallWebMapper {

    public Hall mapToHall(HallRequest hallRequest) {
        return Hall.builder()
                .name(hallRequest.name())
                .address(hallRequest.address())
                .number(hallRequest.number())
                .capacity(hallRequest.capacity())
                .seatImage(hallRequest.seatImage())
                .build();
    }

    public HallResponse mapToHallResponse(Hall hall) {
        return HallResponse.builder()
                .name(hall.getName())
                .address(hall.getAddress())
                .number(hall.getNumber())
                .capacity(hall.getCapacity())
                .seatImage(hall.getSeatImage())
                .createdDate(hall.getCreatedDate())
                .modifiedDate(hall.getModifiedDate())
                .build();
    }

    public Hall mapToHall(Long hallId, HallRequest hallRequest) {
        return Hall.builder()
                .id(hallId)
                .name(hallRequest.name())
                .address(hallRequest.address())
                .number(hallRequest.number())
                .capacity(hallRequest.capacity())
                .seatImage(hallRequest.seatImage())
                .build();
    }
}