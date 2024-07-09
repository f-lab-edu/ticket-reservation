package com.ticketing.solution.adapter.reservation;

import com.ticketing.solution.application.port.in.ReservationOperationPort;
import com.ticketing.solution.adapter.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    private final ReservationOperationPort reservationOperationPort;

    private final ReservationWebMapper reservationWebMapper;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestParam int page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(reservationWebMapper.mapToReservationResponses(reservationOperationPort.getReservations(page, userDetails.getMember())));
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationWebMapper.mapToReservationResponse(reservationOperationPort.getReservationById(reservationId)));
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reservationOperationPort.cancelReservation(reservationId, userDetails.getMember());
        return ResponseEntity.ok().build();
    }
}
