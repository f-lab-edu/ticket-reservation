package com.ticketing.solution.api.reservation;

import com.ticketing.solution.application.ReservationFacade;
import com.ticketing.solution.domain.reservation.ReservationService;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
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

    private final ReservationFacade reservationFacade;

    private final ReservationMapper reservationMapper;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestParam int page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(reservationMapper.mapToReservationResponses(reservationService.getReservations(page, userDetails)));
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationMapper.mapToReservationResponse(reservationService.getReservation(reservationId)));
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationFacade.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
