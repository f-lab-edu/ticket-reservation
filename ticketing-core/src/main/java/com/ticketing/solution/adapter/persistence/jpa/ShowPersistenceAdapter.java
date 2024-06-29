package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.ShowJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.ShowJpaMapper;
import com.ticketing.solution.application.port.out.persistence.ShowPersistencePort;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShowPersistenceAdapter implements ShowPersistencePort {
    private final ShowJpaRepository showJpaRepository;
    private final ShowJpaMapper showJpaMapper;

    @Override
    public Show findById(Long showId) {
        var show = showJpaRepository.findById(showId).orElseThrow();
        return showJpaMapper.mapToDomain(show);
    }

    @Override
    public void save(Show show) {
        showJpaRepository.save(showJpaMapper.mapToEntity(show));
    }

    @Override
    public void deleteById(Long showId) {
        showJpaRepository.deleteById(showId);
    }

    @Override
    public List<Show> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 16, Sort.by(Sort.Direction.DESC, "id"));
        return showJpaMapper.mapToDomains(showJpaRepository.findAll(pageRequest).getContent());
    }
}
