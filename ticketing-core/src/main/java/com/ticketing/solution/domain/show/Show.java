package com.ticketing.solution.domain.show;

import com.ticketing.solution.domain.event.Event;
import com.ticketing.solution.domain.hall.Hall;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "show_round")
public class Show {
    @Id
    @Column(name = "id")
    private int id;

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
}
