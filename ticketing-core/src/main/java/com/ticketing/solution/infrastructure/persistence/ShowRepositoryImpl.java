package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.show.ShowRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.ShowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShowRepositoryImpl implements ShowRepository {
    private final ShowJpaRepository showJpaRepository;
}
