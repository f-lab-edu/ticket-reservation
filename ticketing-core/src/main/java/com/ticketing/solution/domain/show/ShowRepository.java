package com.ticketing.solution.domain.show;

import com.ticketing.solution.domain.member.Member;

import java.util.List;

public interface ShowRepository {
    Show findById(Long showId);

    void save(Show mapToShow);

    void deleteById(Long showId);

    List<Show> findByMember(Member member);
}
