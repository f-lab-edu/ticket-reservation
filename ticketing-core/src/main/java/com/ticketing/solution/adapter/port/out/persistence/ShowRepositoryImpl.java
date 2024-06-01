package com.ticketing.solution.adapter.port.out.persistence;

import com.ticketing.solution.domain.show.ShowRepository;
import com.ticketing.solution.adapter.port.out.persistence.jpa.ShowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShowRepositoryImpl implements ShowRepository {
    private final ShowJpaRepository showJpaRepository;
}
