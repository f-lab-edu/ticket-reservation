package com.ticketing.solution.application.service;

import com.ticketing.solution.application.service.util.WaitingQueueKeyUtil;
import com.ticketing.solution.application.port.in.WaitingOperationPort;
import com.ticketing.solution.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitingService implements WaitingOperationPort {
    private final RedisTemplate<String, String> redisTemplate;
    private final WaitingQueueKeyUtil waitingQueueKeyUtil;

    @Override
    public void registerToWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = waitingQueueKeyUtil.getWaitingQueueKey(showId);
        redisTemplate.opsForZSet().add(waitingQueueKey, member.getEmail(), System.currentTimeMillis());
    }

    @Override
    public void removeFromWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = waitingQueueKeyUtil.getWaitingQueueKey(showId);
        redisTemplate.opsForZSet().remove(waitingQueueKey, member.getEmail());
    }

    @Override
    public int getRankFromWaitingQueue(Long showId, Member member) {
        String waitingQueueKey = waitingQueueKeyUtil.getWaitingQueueKey(showId);
        Long rank = redisTemplate.opsForZSet().rank(waitingQueueKey, member.getEmail());
        return rank != null ? rank.intValue() : -1;
    }

    @Override
    public boolean isExistInActivateQueue(Long showId, Member member) {
        String activateQueueKey = waitingQueueKeyUtil.getActivateQueueUserKey(showId, member.getEmail());
        String score = redisTemplate.opsForValue().get(activateQueueKey);
        return score != null;
    }

}
