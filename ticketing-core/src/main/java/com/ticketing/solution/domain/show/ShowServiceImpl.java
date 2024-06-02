package com.ticketing.solution.domain.show;

import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    @Override
    public Show getShow(Long showId) {
        return showRepository.findById(showId);
    }

    @Override
    public List<Show> getShows(UserDetailsImpl userDetails) {
        return showRepository.findByMember(userDetails.getMember());
    }

    @Override
    public void deleteShow(Long showId) {
        showRepository.deleteById(showId);
    }

    @Override
    public void addShow(Show show) {
        showRepository.save(show);
    }
}
