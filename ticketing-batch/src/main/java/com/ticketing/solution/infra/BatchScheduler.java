package com.ticketing.solution.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final ApplicationContext context;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${job.lock.key}")
    private String lockKey;

    @Value("${scheduler.job.name}")
    private String jobName;

    @Scheduled(cron = "${cronexpression}")
    public void runJob() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean acquired = ops.setIfAbsent(lockKey, "locked", 3, TimeUnit.MINUTES);

        if (acquired != null && acquired) {
            try {
                Job job = (Job) context.getBean(jobName);
                jobLauncher.run(job, new JobParametersBuilder()
                        .addLong("startAt", System.currentTimeMillis())
                        .toJobParameters());
            } catch (Exception e) {
                log.error("Failed to run job", e);
            } finally {
                redisTemplate.delete(lockKey);
            }
        } else {
            log.warn("Job is already running");
        }
    }
}