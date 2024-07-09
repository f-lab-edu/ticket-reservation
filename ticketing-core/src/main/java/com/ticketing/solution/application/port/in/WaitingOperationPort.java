package com.ticketing.solution.application.port.in;

import com.ticketing.solution.domain.member.Member;

public interface WaitingOperationPort {
    void registerToWaitingQueue(Long showId, Member member);

    int getRankFromWaitingQueue(Long showId, Member member);

    boolean isExistInActivateQueue(Long showId, Member member);

    void removeFromWaitingQueue(Long showId, Member member);
}
