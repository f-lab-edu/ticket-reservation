package com.ticketing.solution.api.event;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.event.EventService;
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

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping("/public/events")
    public ResponseEntity<List<EventResponse>> getEvents(@RequestParam int page, @RequestParam EventType eventType) {
        List<Event> events = eventService.getEvents(page, eventType);
        List<EventResponse> response = eventMapper.mapToEventList(events);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/events/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long eventId) {
        Event event = eventService.getEvent(eventId);
        EventResponse response = eventMapper.mapToEventResponse(event);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> addEvent(@Valid @RequestBody EventRequest eventRequest) {
        eventService.addEvent(eventMapper.mapToEvent(eventRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long eventId,
                                            @Valid @RequestBody EventRequest eventRequest) {
        eventService.updateEvent(eventId, eventMapper.mapToEvent(eventRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> removeEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }

}
