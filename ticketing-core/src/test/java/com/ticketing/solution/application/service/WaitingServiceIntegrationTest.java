package com.ticketing.solution.application.service;

import com.ticketing.solution.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class WaitingServiceIntegrationTest {

    @Container
    public GenericContainer<?> redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);

    private RedisTemplate<String, String> redisTemplate;
    private WaitingService waitingService;

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

        waitingService = new WaitingService(redisTemplate);
    }

    @Test
    public void registerToWaitingQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("test@gmail.com")
                .build();


        waitingService.registerToWaitingQueue(showId, member);

        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        assertTrue(zSetOperations.range("waitingQueue:" + showId, 0, -1).contains(member.getEmail()));
    }

    @Test
    public void removeFromWaitingQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("test@gmail.com")
                .build();

        waitingService.registerToWaitingQueue(showId, member);
        waitingService.removeFromWaitingQueue(showId, member);

        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        assertFalse(zSetOperations.range("waitingQueue:" + showId, 0, -1).contains(member.getEmail()));
    }

    @Test
    public void getRankFromWaitingQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("test@example.com")
                .build();

        waitingService.registerToWaitingQueue(showId, member);

        Long rank = redisTemplate.opsForZSet().rank("waitingQueue:" + showId, member.getEmail());
        assertNotNull(rank);
        assertEquals(0, rank);
    }

    @Test
    public void isExistInActivateQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("test@example.com")
                .build();

        redisTemplate.opsForValue().set("activateQueue:" + member.getEmail(), "1");

        boolean exists = waitingService.isExistInActivateQueue(showId, member);

        assertTrue(exists);
    }
}