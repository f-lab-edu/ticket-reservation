package com.ticketing.solution.adapter.waiting;

import com.ticketing.solution.adapter.security.UserDetailsImpl;
import com.ticketing.solution.application.port.in.WaitingOperationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class WaitingController {

    private final WaitingOperationPort waitingOperationPort;
    private final WaitingMapper waitingMapper;


    @PostMapping("/waiting/{showId}")
    public ResponseEntity<Void> register(@PathVariable Long showId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        waitingOperationPort.registerToWaitingQueue(showId, userDetails.getMember());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/waiting/{showId}")
    public ResponseEntity<Void> unregister(@PathVariable Long showId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        waitingOperationPort.removeFromWaitingQueue(showId, userDetails.getMember());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/waiting/{showId}")
    public ResponseEntity<WaitingResponse> getRank(@PathVariable Long showId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(waitingMapper.mapToResponse(waitingOperationPort.getRankFromWaitingQueue(showId, userDetails.getMember())));
    }

    @GetMapping("/waiting/allowed/{showId}")
    public ResponseEntity<WaitingResponse> isAllowed(@PathVariable Long showId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(waitingMapper.mapToResponse(waitingOperationPort.isExistInActivateQueue(showId, userDetails.getMember())));
    }
}
