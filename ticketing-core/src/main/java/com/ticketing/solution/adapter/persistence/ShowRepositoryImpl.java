package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.ShowJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.ShowMapper;
import com.ticketing.solution.application.port.out.ShowRepository;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShowRepositoryImpl implements ShowRepository {
    private final ShowJpaRepository showJpaRepository;
    private final ShowMapper showMapper;

    @Override
    public Show findById(Long showId) {
        var show = showJpaRepository.findById(showId).orElseThrow();
        return showMapper.mapToDomain(show);
    }

    @Override
    public void save(Show show) {
        showJpaRepository.save(showMapper.mapToEntity(show));
    }

    @Override
    public void deleteById(Long showId) {
        showJpaRepository.deleteById(showId);
    }

    @Override
    public List<Show> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "id"));
        return showMapper.mapToDomains(showJpaRepository.findAll(pageRequest).getContent());
    }
}
