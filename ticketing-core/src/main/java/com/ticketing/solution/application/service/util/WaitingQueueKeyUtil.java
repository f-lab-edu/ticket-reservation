package com.ticketing.solution.application.service.util;

import org.springframework.stereotype.Component;

@Component
public class WaitingQueueKeyUtil {

    private static final String WAITING_QUEUE_PREFIX = "waitingQueue:";
    private static final String ACTIVATE_QUEUE_PREFIX = "activateQueue:";

    public String getWaitingQueueKey(Long showId) {
        return WAITING_QUEUE_PREFIX + showId;
    }

    public String getActivateQueueKeyPattern(Long showId) {
        return ACTIVATE_QUEUE_PREFIX + showId + ":*";
    }

    public String getActivateQueueUserKey(Long showId, String user) {
        return ACTIVATE_QUEUE_PREFIX + showId + ":" + user;
    }
}
