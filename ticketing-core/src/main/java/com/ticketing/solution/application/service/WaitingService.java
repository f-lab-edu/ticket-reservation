package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.WaitingOperationPort;
import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitingService implements WaitingOperationPort {

    private static final String WAITING_QUEUE_KEY = "waitingQueue:";
    private static final String ACTIVATE_QUEUE_KEY = "activateQueue:";

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void registerToWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = WAITING_QUEUE_KEY + showId;
        redisTemplate.opsForZSet().add(waitingQueueKey, member.getEmail(), System.currentTimeMillis());
    }

    @Override
    public void removeFromWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = WAITING_QUEUE_KEY + showId;
        redisTemplate.opsForZSet().remove(waitingQueueKey, member.getEmail());
    }

    @Override
    public int getRankFromWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = WAITING_QUEUE_KEY + showId;
        Long rank = redisTemplate.opsForZSet().rank(waitingQueueKey, member.getEmail());
        return rank != null ? rank.intValue() : -1;
    }

    @Override
    public boolean isExistInActivateQueue(Long showId, Member member) {
        String activateQueueKey = ACTIVATE_QUEUE_KEY + member.getEmail();
        String score = redisTemplate.opsForValue().get(activateQueueKey);
        return score != null;
    }

}
