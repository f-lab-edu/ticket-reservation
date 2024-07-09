package com.ticketing.solution.application.service;

import com.ticketing.solution.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class WaitingServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ZSetOperations<String, String> zSetOperations;

    private WaitingService waitingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);
        waitingService = new WaitingService(redisTemplate);
    }

    @Test
    public void testRegisterToWaitingQueue() {
        Long showId = 1L;

        Member member = Member.builder()
                .email("test@gmail.com")
                .build();

        waitingService.registerToWaitingQueue(showId, member);

        verify(zSetOperations, times(1)).add(eq("waitingQueue:" + showId), eq(member.getEmail()), anyDouble());
    }

    @Test
    public void testRemoveFromWaitingQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("test@gmail.com")
                .build();

        waitingService.removeFromWaitingQueue(showId, member);

        verify(zSetOperations, times(1)).remove(eq("waitingQueue:" + showId), eq(member.getEmail()));
    }

    @Test
    public void getRankFromWaitingQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("warmth424@gmail.com")
                .build();

        when(zSetOperations.rank("waitingQueue:" + showId, member.getEmail())).thenReturn(1L);

        int rank = waitingService.getRankFromWaitingQueue(showId, member);

        assertEquals(1, rank);
        verify(zSetOperations, times(1)).rank("waitingQueue:" + showId, member.getEmail());
    }

    @Test
    public void isExistInActivateQueue() {
        Long showId = 1L;
        Member member = Member.builder()
                .email("warmth424@gmail.com")
                .build();

        when(redisTemplate.opsForValue().get("activateQueue:" + member.getEmail())).thenReturn("1");

        boolean exists = waitingService.isExistInActivateQueue(showId, member);

        assertTrue(exists);
        verify(redisTemplate.opsForValue(), times(1)).get("activateQueue:" + member.getEmail());
    }

}