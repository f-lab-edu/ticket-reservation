package com.ticketing.solution.api.hall;

import com.ticketing.solution.domain.hall.Hall;
import com.ticketing.solution.domain.hall.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HallController {

    private final HallService hallService;
    private final HallMapper hallMapper;

    @GetMapping("/public/halls/{hallId}")
    public ResponseEntity<HallResponse> getHall(@PathVariable Long hallId) {
        Hall hall = hallService.getHall(hallId);
        HallResponse response = hallMapper.mapToHallResponse(hall);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/halls")
    public ResponseEntity<HallResponse> addHall(HallRequest hallRequest) {
        hallService.addHall(hallMapper.mapToHall(hallRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/halls/{hallId}")
    public ResponseEntity<HallResponse> updateHall(@PathVariable Long hallId, HallRequest hallRequest) {
        hallService.updateHall(hallId, hallMapper.mapToHall(hallRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/halls/{hallId}")
    public ResponseEntity<HallResponse> removeHall(@PathVariable Long hallId) {
        hallService.deleteHall(hallId);
        return ResponseEntity.ok().build();
    }
}
