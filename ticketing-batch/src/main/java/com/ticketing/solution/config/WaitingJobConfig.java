package com.ticketing.solution.config;

import com.ticketing.solution.application.port.in.ShowOperationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WaitingJobConfig {

    private static final String WAITING_QUEUE_KEY = "waitingQueue:";
    private static final String ACTIVATE_QUEUE_KEY = "activateQueue:";

    @Value("${job.waitingJob.showId}")
    private Long showId;

    @Value("${job.waitingJob.timeout}")
    private Long timeout;

    private final RedisTemplate<String, String> redisTemplate;
    private final ShowOperationPort showOperationPort;

    @Bean
    public Job waitingJob(JobRepository jobRepository, Step waitingStep) {
        return new JobBuilder("waitingJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(waitingStep)
                .build();
    }

    @Bean
    public Step waitingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("waitingStep", jobRepository)
                .tasklet(moveUserToActivateQueue(), transactionManager)
                .build();
    }

    private Tasklet moveUserToActivateQueue() {
        return (contribution, chunkContext) -> {
            int maxOccupancy = showOperationPort.getShow(showId).getMaxOccupancy();
            int activateQueueUsage = redisTemplate.opsForZSet().size(WAITING_QUEUE_KEY + showId).intValue();
            moveUsers(maxOccupancy - activateQueueUsage);
            return RepeatStatus.FINISHED;
        };
    }

    public void moveUsers(int count) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        Set<String> users = zSetOps.range(WAITING_QUEUE_KEY + showId, 0, (count - 1));
        if (users != null) {
            for (String user : users) {
                zSetOps.remove(WAITING_QUEUE_KEY + showId, user);
                valueOps.set(ACTIVATE_QUEUE_KEY + user, user);
                redisTemplate.expire(ACTIVATE_QUEUE_KEY + user, timeout, TimeUnit.MINUTES);
            }
        }
    }
}
