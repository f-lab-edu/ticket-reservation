package com.ticketing.solution.domain.hall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Hall {

    private Long id;
    private String name;
    private String address;
    private int number;
    private int capacity;
    private String seatImage;  // Image link
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void update(Hall hall) {
        this.name = hall.getName();
        this.address = hall.getAddress();
        this.number = hall.getNumber();
        this.capacity = hall.getCapacity();
        this.seatImage = hall.getSeatImage();
    }
}
