package com.ticketing.solution.adapter.seat;

import com.ticketing.solution.application.port.in.SeatClassOperationPort;
import com.ticketing.solution.application.port.in.ShowOperationPort;
import com.ticketing.solution.domain.seat.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SeatWebMapper {

    private final ShowOperationPort showOperationPort;
    private final SeatClassOperationPort seatClassOperationPort;

    public SeatResponse mapToResponse(Seat seat) {
        return SeatResponse.builder()
                .type(seat.getSeatClass().getName())
                .capacity(seat.getCapacity())
                .price(seat.getSeatClass().getPrice())
                .build();
    }

    public List<SeatResponse> mapToResponse(List<Seat> seats) {
        return seats.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Seat mapToDomain(SeatRequest request) {
        return Seat.builder()
                .capacity(request.capacity())
                .seatClass(seatClassOperationPort.getSeatClass(request.SeatClassId()))
                .show(showOperationPort.getShow(request.ShowId()))
                .build();
    }
}
