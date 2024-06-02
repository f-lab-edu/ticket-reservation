package com.ticketing.solution.infrastructure.persistence;

import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.show.Show;
import com.ticketing.solution.domain.show.ShowRepository;
import com.ticketing.solution.infrastructure.persistence.jpa.ShowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShowRepositoryImpl implements ShowRepository {
    private final ShowJpaRepository showJpaRepository;

    @Override
    public Show findById(Long showId) {
        return showJpaRepository.findById(showId).orElseThrow();
    }

    @Override
    public void save(Show mapToShow) {
        showJpaRepository.save(mapToShow);
    }

    @Override
    public void deleteById(Long showId) {
        showJpaRepository.deleteById(showId);
    }

    @Override
    public List<Show> findByMember(Member member) {
        return showJpaRepository.findByMember(member);
    }
}
