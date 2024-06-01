package com.ticketing.solution.adapter.port.out.persistence;

import com.ticketing.solution.domain.hall.HallRepository;
import com.ticketing.solution.adapter.port.out.persistence.jpa.HallJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HallRepositoryImpl implements HallRepository {
    private final HallJpaRepository hallRepository;

}
