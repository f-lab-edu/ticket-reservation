package com.ticketing.solution.domain.seat;

import com.ticketing.solution.domain.show.Show;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SeatClass {
    private int id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Show show;

}
