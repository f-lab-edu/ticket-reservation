package com.ticketing.solution.adapter.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "show_round")
@EntityListeners(AuditingEntityListener.class)
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "max_occupancy", nullable = false, columnDefinition = "int default 1")
    private int maxOccupancy;

    @Column(name = "play_time", nullable = false)
    private int playTime;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public void update(Show mapToShow, Event event, Hall hall) {
        this.eventDate = mapToShow.getEventDate();
        this.maxOccupancy = mapToShow.getMaxOccupancy();
        this.playTime = mapToShow.getPlayTime();
        this.location = mapToShow.getLocation();
        this.event = event;
        this.hall = hall;
    }
}
