package com.ticketing.solution.adapter.seat;

import com.ticketing.solution.adapter.security.UserDetailsImpl;
import com.ticketing.solution.application.port.in.SeatOperationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SeatController {

    private final SeatOperationPort seatOperationPort;
    private final SeatWebMapper seatWebMapper;

    @GetMapping("/seats/{seatId}")
    public ResponseEntity<SeatResponse> getSeat(@PathVariable Long seatId) {
        return ResponseEntity.ok(seatWebMapper.mapToResponse(seatOperationPort.getSeat(seatId)));
    }

    @GetMapping("/seats}")
    public ResponseEntity<List<SeatResponse>> getSeats(@RequestParam Long showId) {
        return ResponseEntity.ok(seatWebMapper.mapToResponse(seatOperationPort.getSeats(showId)));
    }

    @PostMapping("/seats")
    public ResponseEntity<Void> addSeat(SeatRequest seatRequest) {
        seatOperationPort.addSeat(seatWebMapper.mapToDomain(seatRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/seats/{seatId}")
    public ResponseEntity<Void> occupySeat(@PathVariable Long seatId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        seatOperationPort.occupySeat(seatId, userDetails.getMember());
        return ResponseEntity.ok().build();
    }
}
