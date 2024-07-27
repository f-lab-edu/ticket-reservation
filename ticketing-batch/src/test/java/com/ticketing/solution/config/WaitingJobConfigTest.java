package com.ticketing.solution.config;

import com.ticketing.solution.application.port.in.ShowOperationPort;
import com.ticketing.solution.application.service.util.WaitingQueueKeyUtil;
import com.ticketing.solution.domain.show.Show;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
public class WaitingJobConfigTest {

    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ShowOperationPort showOperationPort;

    private WaitingQueueKeyUtil waitingQueueKeyUtil;

    private WaitingJobConfig waitingJobConfig;

    @Container
    public GenericContainer<?> redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);

    private static final Long SHOW_ID = 1L;
    private static final Long TIMEOUT = 30L;
    private static final int MAX_USERS = 100;

    @BeforeEach
    public void setup() {
        redis.start();
        String redisContainerIp = redis.getHost();
        Integer redisContainerPort = redis.getMappedPort(6379);

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisContainerIp, redisContainerPort));
        lettuceConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

        waitingQueueKeyUtil = new WaitingQueueKeyUtil();
        waitingJobConfig = new WaitingJobConfig(redisTemplate, showOperationPort, waitingQueueKeyUtil);
    }

    @Test
    public void shouldMoveUsersWhenAvailableSlotsArePresent() {
        Show show = Show.builder()
            .id(SHOW_ID)
            .maxOccupancy(MAX_USERS)
            .build();
        when(showOperationPort.getShow(SHOW_ID)).thenReturn(show);

        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        for (int i = 0; i < 5; i++) {
            zSetOps.add(waitingQueueKeyUtil.getWaitingQueueKey(SHOW_ID), "user" + i + "@example.com", System.currentTimeMillis());
        }

        waitingJobConfig.moveUsers(5);
        int activateQueueUserCount = Objects.requireNonNull(redisTemplate.keys(waitingQueueKeyUtil.getActivateQueueKeyPattern(SHOW_ID))).size();

        assertEquals(5, activateQueueUserCount);
    }

    @Test
    public void shouldNotMoveUsersWhenNoAvailableSlots() {
        Show show = Show.builder()
                .id(SHOW_ID)
                .maxOccupancy(MAX_USERS)
                .build();
        when(showOperationPort.getShow(SHOW_ID)).thenReturn(show);

        waitingJobConfig.moveUsers(0);

        int activateQueueUserCount = Objects.requireNonNull(redisTemplate.keys(waitingQueueKeyUtil.getActivateQueueKeyPattern(SHOW_ID))).size();

        assertEquals(0, activateQueueUserCount);
    }
}