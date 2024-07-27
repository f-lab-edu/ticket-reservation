package com.ticketing.solution.config;

import com.ticketing.solution.application.service.util.WaitingQueueKeyUtil;
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

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WaitingJobConfig {
    @Value("${job.waitingJob.showId}")
    private Long showId;

    @Value("${job.waitingJob.timeout}")
    private Long timeout;

    private final RedisTemplate<String, String> redisTemplate;
    private final ShowOperationPort showOperationPort;
    private final WaitingQueueKeyUtil waitingQueueKeyUtil;

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

    Tasklet moveUserToActivateQueue() {
        return (contribution, chunkContext) -> {
            int maxOccupancy = showOperationPort.getShow(showId).getMaxOccupancy();
            int activateQueueUsage = Objects.requireNonNull(redisTemplate.keys(waitingQueueKeyUtil.getActivateQueueKeyPattern(showId))).size();
            int availableSlots = maxOccupancy - activateQueueUsage;
            if (availableSlots > 0) {
                moveUsers(availableSlots);
            }
            return RepeatStatus.FINISHED;
        };
    }

    public void moveUsers(int count) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String waitingQueueKey = waitingQueueKeyUtil.getWaitingQueueKey(showId);

        Set<String> users = zSetOps.range(waitingQueueKey, 0, count - 1);
        if (Objects.requireNonNull(users).size() != 0) {
            for (String user : users) {
                String activateQueueUserKey = waitingQueueKeyUtil.getActivateQueueUserKey(showId, user);
                zSetOps.remove(waitingQueueKey, user);
                valueOps.set(activateQueueUserKey, user);
                redisTemplate.expire(activateQueueUserKey, timeout, TimeUnit.MINUTES);
            }
        }
    }
}
