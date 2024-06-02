package com.ticketing.solution.api.show;

import com.ticketing.solution.api.event.EventMapper;
import com.ticketing.solution.api.hall.HallMapper;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShowMapper {

    private final HallMapper hallMapper;

    private final EventMapper eventMapper;

    public ShowResponse mapToShowResponse(Show show) {
        return ShowResponse.builder()
                .eventDate(show.getEventDate())
                .maxOccupancy(show.getMaxOccupancy())
                .playTime(show.getPlayTime())
                .location(show.getLocation())
                .createdDate(show.getCreatedDate())
                .modifiedDate(show.getModifiedDate())
                .hall(hallMapper.mapToHallResponse(show.getHall()))
                .event(eventMapper.mapToEventResponse(show.getEvent()))
                .build();
    }

    public List<ShowResponse> mapToShowsResponse(List<Show> shows) {
        return shows.stream()
                .map(this::mapToShowResponse)
                .collect(Collectors.toList());
    }

    public Show mapToShow(ShowRequest showRequest) {
        return Show.builder()
                .eventDate(showRequest.eventDate())
                .maxOccupancy(showRequest.maxOccupancy())
                .playTime(showRequest.playTime())
                .location(showRequest.location())
                .build();
    }

}
