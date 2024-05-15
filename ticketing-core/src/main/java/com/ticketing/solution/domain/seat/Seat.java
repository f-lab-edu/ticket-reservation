package com.ticketing.solution.domain.seat;

import com.ticketing.solution.domain.hall.Hall;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "seat_class_id", nullable = false)
    private SeatClass seatClass;

}
