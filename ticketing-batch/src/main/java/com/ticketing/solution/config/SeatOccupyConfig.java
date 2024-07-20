package com.ticketing.solution.config;

import com.ticketing.solution.application.port.in.SeatOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatOccupyPersistencePort;
import com.ticketing.solution.domain.seat.SeatOccupy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SeatOccupyConfig {

    @Value("${job.seatOccupyJob.showId}")
    private Long showId;

    private final SeatOccupyPersistencePort seatOccupyPersistencePort;

    private final SeatOperationPort seatOperationPort;

    @Bean
    public Job seatOccupyJob(JobRepository jobRepository, Step seatOccupyStep) {
        return new JobBuilder("seatOccupyJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(seatOccupyStep)
                .build();
    }

    @Bean
    public Step seatOccupyStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("seatOccupyStep", jobRepository)
                .tasklet(releaseSeatOccupancy(), transactionManager)
                .build();
    }

    private Tasklet releaseSeatOccupancy() {
        return (contribution, chunkContext) -> {
            LocalDateTime seatOccupyLimit = LocalDateTime.now().minus(Duration.ofMinutes(7));
            List<SeatOccupy> releasedSeatOccupyList = seatOccupyPersistencePort.releaseSeatOccupancy(seatOccupyLimit);
            for (SeatOccupy seatOccupy : releasedSeatOccupyList) {
                Long seatId = seatOccupy.getSeat().getId();
                seatOperationPort.increaseSeatCapacity(seatId);
            }
            return null;
        };
    }

}
