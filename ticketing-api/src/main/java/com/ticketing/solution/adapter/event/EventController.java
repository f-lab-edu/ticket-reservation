package com.ticketing.solution.adapter.event;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.application.port.in.EventOperationPort;
import com.ticketing.solution.domain.event.EventType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventOperationPort eventOperationPort;
    private final EventWebMapper eventWebMapper;

    @GetMapping("/public/events")
    public ResponseEntity<List<EventResponse>> getEvents(@RequestParam int page, @RequestParam EventType eventType) {
        List<Event> events = eventOperationPort.getEvents(page, eventType);
        List<EventResponse> response = eventWebMapper.mapToEventList(events);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/events/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long eventId) {
        Event event = eventOperationPort.getEvent(eventId);
        EventResponse response = eventWebMapper.mapToEventResponse(event);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> addEvent(@Valid @RequestBody EventRequest eventRequest) {
        eventOperationPort.addEvent(eventWebMapper.mapToEvent(eventRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long eventId,
                                            @Valid @RequestBody EventRequest eventRequest) {
        eventOperationPort.updateEvent(eventId, eventWebMapper.mapToEvent(eventRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> removeEvent(@PathVariable Long eventId) {
        eventOperationPort.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }

}
