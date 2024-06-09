package com.ticketing.solution.adapter.reservation;

import com.ticketing.solution.application.port.in.ReservationService;
import com.ticketing.solution.adapter.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationWebMapper reservationWebMapper;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestParam int page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(reservationWebMapper.mapToReservationResponses(reservationService.getReservations(page, userDetails.getMember())));
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationWebMapper.mapToReservationResponse(reservationService.getReservation(reservationId)));
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
