package com.ticketing.solution.api.show;

import com.ticketing.solution.application.ShowFacade;
import com.ticketing.solution.domain.show.ShowService;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ShowController {

    private final ShowService showService;

    private final ShowFacade showFacade;

    private final ShowMapper showMapper;

    @GetMapping("/public/shows")
    public ResponseEntity<List<ShowResponse>> getShows(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(showMapper.mapToShowsResponse(showService.getShows(userDetails)));
    }

    @GetMapping("/public/shows/{showId}")
    public ResponseEntity<ShowResponse> getShow(@PathVariable Long showId) {
        return ResponseEntity.ok(showMapper.mapToShowResponse(showService.getShow(showId)));
    }

    @PostMapping("/shows")
    public ResponseEntity<Void> addShow(ShowRequest showRequest) {
        showFacade.addShow(showMapper.mapToShow(showRequest), showRequest.eventId(), showRequest.hallId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/shows/{showId}")
    public ResponseEntity<Void> updateShow(@PathVariable Long showId, ShowRequest showRequest) {
        showFacade.updateShow(showId, showMapper.mapToShow(showRequest), showRequest.eventId(), showRequest.hallId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/shows/{showId}")
    public ResponseEntity<Void> removeShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.ok().build();
    }

}

