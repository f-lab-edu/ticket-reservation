package com.ticketing.solution.adapter.persistence.jpa.mapper;

import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowJpaMapper {

    private final EventJpaMapper EventJpaMapper;
    private final HallJpaMapper HallJpaMapper;

    public Show mapToDomain(com.ticketing.solution.adapter.persistence.jpa.entity.Show show) {
        return Show.builder()
                .id(show.getId())
                .location(show.getLocation())
                .maxOccupancy(show.getMaxOccupancy())
                .playTime(show.getPlayTime())
                .eventDate(show.getEventDate())
                .event(EventJpaMapper.mapToDomain(show.getEvent()))
                .hall(HallJpaMapper.mapToDomain(show.getHall()))
                .build();
    }

    public com.ticketing.solution.adapter.persistence.jpa.entity.Show mapToEntity(Show show) {
        return com.ticketing.solution.adapter.persistence.jpa.entity.Show.builder()
                .id(show.getId())
                .location(show.getLocation())
                .maxOccupancy(show.getMaxOccupancy())
                .playTime(show.getPlayTime())
                .eventDate(show.getEventDate())
                .build();
    }

    public List<Show> mapToDomains(List<com.ticketing.solution.adapter.persistence.jpa.entity.Show> shows) {
        return shows.stream()
                .map(this::mapToDomain)
                .toList();
    }

}
